package com.chavaillaz.awsec2utils.api.implementation.arc.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;
import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.aws.AwsService;
import com.chavaillaz.awsec2utils.api.implementation.common.AuthService_A;
import com.chavaillaz.awsec2utils.api.model.VmTemplate;
import com.chavaillaz.awsec2utils.api.specification.arc.service.TerminateService_I;
import com.chavaillaz.awsec2utils.api.specification.aws.service.DescribeInstanceService_I;
import com.chavaillaz.awsec2utils.utils.VmState;

/**
 * Implementation of {@link TerminateService_I}
 * 
 * @author Johan Chavaillaz
 */
public class TerminateService extends AuthService_A implements TerminateService_I {
	
	private static final Logger logger = LogManager.getLogger(TerminateService.class);

	public TerminateService(AmazonEC2Client aws) {
		super(aws);
	}

	public void terminate(String vmId, Boolean synchrone) throws Exception {
		logger.info("Getting template information ...");
		VmTemplate template = VmTemplate.getTemplate(vmId);
		
		if (template == null) {
			logger.error("VM Template not found. Please create one with <define> command.");
			return;
		}
		
		DescribeInstanceService_I describeInstanceService = AwsService.getInstance().getDescribeInstanceService(aws);
		Instance instance = describeInstanceService.getFirstSignificantInstance(new Tag(Constants.TAG_KEY, vmId));
		
		if (instance == null) {
			logger.error("No instance is presently running or stopped with this template.");
			return;
		}
		
		if (VmState.isUnstableState(instance)) {
			logger.info("Instance state is already changing. Please wait until the state changed ...");
		} 
		
		while (VmState.isUnstableState(instance)) {
			Thread.sleep(1000);
			instance = describeInstanceService.getFirstSignificantInstance(new Tag(Constants.TAG_KEY, vmId));
		}
		
		if (VmState.isTerminated(instance)) {
			logger.error("Instance is already terminated ...");
		} 
		else {
			logger.info("Terminating instance ...");
			
			AwsService.getInstance().getTerminateInstanceService(aws).terminateInstance(instance);
			
			if (synchrone) {
				AwsService.getInstance().getWaitInstanceService(aws).waitInstance(instance, VmState.TERMINATED);
				logger.info("Instance is now terminated !");
			}
		}
	}

	public void terminateAll(Boolean synchrone) throws Exception {
		logger.info("Terminating all instance ...");
		
		List<Instance> listInstance = AwsService.getInstance().getListInstanceService(aws).getListInstance();
		List<Instance> listInstanceActive = new ArrayList<Instance>();
		
		for (Instance instance : listInstance) {
			if (VmState.isUnstableState(instance)) {
				logger.info("State of instance " + instance.getInstanceId() + " is already changing. Please wait until the state changed ...");
			} 
			
			while (VmState.isUnstableState(instance)) {
				Thread.sleep(1000);
				instance = AwsService.getInstance().getDescribeInstanceService(aws).getInstance(instance.getInstanceId());
			}
			
			if (!VmState.isTerminated(instance)) {
				logger.info("Terminating instance " + instance.getInstanceId() + " ...");
				AwsService.getInstance().getTerminateInstanceService(aws).terminateInstance(instance);
				listInstanceActive.add(instance);
			}
		}
		
		if (listInstanceActive.size() > 0) {
			if (synchrone) {
				logger.info("Waiting all instances to terminate");
				AwsService.getInstance().getWaitInstanceService(aws).waitInstance(listInstanceActive, VmState.TERMINATED);
				logger.info("All running instances are now terminated !");
			}
		} 
		else {
			logger.info("No running instance to terminate found.");
		}
	}

	public void terminate(String vmId) throws Exception {
		terminate(vmId, true);
	}

	public void terminateAll() throws Exception {
		terminateAll(true);
	}

}
