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
import com.chavaillaz.awsec2utils.api.specification.arc.service.StopService_I;
import com.chavaillaz.awsec2utils.api.specification.aws.service.DescribeInstanceService_I;
import com.chavaillaz.awsec2utils.utils.VmState;

/**
 * Implementation of {@link StopService_I}
 * 
 * @author Johan Chavaillaz
 */
public class StopService extends AuthService_A implements StopService_I {
	
	private static final Logger logger = LogManager.getLogger(StopService.class);

	public StopService(AmazonEC2Client aws) {
		super(aws);
	}

	public void stop(String vmId, Boolean synchrone) throws Exception {
		logger.info("Getting template information ...");
		VmTemplate template = VmTemplate.getTemplate(vmId);
		
		if (template == null) {
			logger.error("VM Template not found. Please create one with <define> command.");
			return;
		}
		
		DescribeInstanceService_I describeInstanceService = AwsService.getInstance().getDescribeInstanceService(aws);
		Instance instance = describeInstanceService.getFirstInstance(new Tag(Constants.TAG_KEY, vmId));
		
		if (instance == null) {
			logger.info("No instance is presently running or stopped with this template.");
			return;
		}
		
		if (VmState.isUnstableState(instance)) {
			logger.info("Instance state is already changing. Please wait until the state changed ...");
		} 
		
		while (VmState.isUnstableState(instance)) {
			Thread.sleep(1000);
			instance = describeInstanceService.getFirstInstance(new Tag(Constants.TAG_KEY, vmId));
		}
		
		if (VmState.isStopped(instance)) {
			logger.error("Instance is already stopped ...");
		} 
		else if (VmState.isRunning(instance)) {
			logger.info("Stopping instance ...");
			
			AwsService.getInstance().getStopInstanceService(aws).stopInstance(instance);
			
			if (synchrone) {
				AwsService.getInstance().getWaitInstanceService(aws).waitInstance(instance, VmState.STOPPED);
				logger.info("Instance is now stopped !");
			}
		} 
		else {
			logger.error("Instance must be in running state to stop it ...");
		}
	}

	public void stopAll(Boolean synchrone) throws Exception {
		logger.info("Stopping all instance ...");
		
		List<Instance> listInstance = AwsService.getInstance().getListInstanceService(aws).getListInstance();
		List<Instance> listInstanceRunning = new ArrayList<Instance>();
		
		for (Instance instance : listInstance) {
			if (VmState.isUnstableState(instance)) {
				logger.info("State of instance " + instance.getInstanceId() + " is already changing. Please wait until the state changed ...");
			} 
			
			while (VmState.isUnstableState(instance)) {
				Thread.sleep(1000);
				instance = AwsService.getInstance().getDescribeInstanceService(aws).getInstance(instance.getInstanceId());
			}
			
			if (VmState.isRunning(instance)) {
				logger.info("Stopping instance " + instance.getInstanceId() + " ...");
				AwsService.getInstance().getStopInstanceService(aws).stopInstance(instance);
				listInstanceRunning.add(instance);
			}
		}
		
		if (listInstanceRunning.size() > 0) {
			if (synchrone) {
				logger.info("Waiting all instances to stop");
				AwsService.getInstance().getWaitInstanceService(aws).waitInstance(listInstanceRunning, VmState.STOPPED);
				logger.info("All running instances are now stopped !");
			}
		} 
		else {
			logger.info("No running instance to stop found.");
		}
	}

	public void stop(String vmId) throws Exception {
		stop(vmId, true);
	}

	public void stopAll() throws Exception {
		stopAll(true);
	}

}
