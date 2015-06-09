package com.chavaillaz.awsec2utils.api.implementation.arc.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;
import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.aws.AwsService;
import com.chavaillaz.awsec2utils.api.implementation.common.AuthService_A;
import com.chavaillaz.awsec2utils.api.model.Configuration;
import com.chavaillaz.awsec2utils.api.model.VmTemplate;
import com.chavaillaz.awsec2utils.api.specification.arc.service.StartService_I;
import com.chavaillaz.awsec2utils.api.specification.aws.service.TagInstanceService_I;
import com.chavaillaz.awsec2utils.utils.VmState;

/**
 * Implementation of {@link StartService_I}
 * 
 * @author Johan Chavaillaz
 */
public class StartService extends AuthService_A implements StartService_I {
	
	private static final Logger logger = LogManager.getLogger(StartService.class);

	public StartService(AmazonEC2Client aws) {
		super(aws);
	}

	public void start(String vmId, Boolean synchrone) throws Exception {
		logger.info("Getting template information ...");
		VmTemplate template = VmTemplate.getTemplate(vmId);
		
		if (template == null) {
			logger.error("VM Template not found. Please create one with <define> command.");
			return;
		}
		
		logger.info("Getting instance state information ...");
		Instance instance = AwsService.getInstance().getDescribeInstanceService(aws).getFirstSignificantInstance(new Tag(Constants.TAG_KEY, vmId));
		
		if (VmState.isUnstableState(instance)) {
			logger.error("Instance state is changing. Please retry in a few seconds ...");
		} 
		else if (VmState.isRunning(instance)) {
			logger.error("Instance is already running ...");
		} 
		else if (VmState.isStopped(instance)) {
			logger.info("Instance is currently stopped.");
			logger.info("Starting instance ...");
			AwsService.getInstance().getStartInstanceService(aws).startInstance(instance);
			
			if (synchrone) {
				logger.info("Waiting for the start of the instance ...");
				AwsService.getInstance().getWaitInstanceService(aws).waitInstance(instance);
				logger.info("Instance is now running !");
			}
		} 
		else {
			logger.info("Instance is currently terminated.");
			
			logger.info("Getting configuration ...");
			Configuration configuration = Configuration.getConfiguration();
			
			logger.info("Starting instance ...");
			List<Instance> listInstance = AwsService.getInstance().getStartInstanceService(aws).runInstance(
							template.getImageId(), 
							template.getInstanceType(), 
							configuration.getKeyPairName(), 
							template.getSecurityGroup());
			
			logger.info("Tagging instance ...");
			TagInstanceService_I tagInstanceService = AwsService.getInstance().getTagInstanceService(aws);
			// Used to manage all instances created by this tool
			tagInstanceService.tagInstance(listInstance, new Tag(Constants.TAG_GROUP_KEY, Constants.TAG_GROUP_DEFAULT));
			// Used to manage the current instance in particular
			tagInstanceService.tagInstance(listInstance, new Tag(Constants.TAG_KEY, vmId));
			
			if (synchrone) {
				logger.info("Waiting for the start of the instance ...");
				AwsService.getInstance().getWaitInstanceService(aws).waitInstance(listInstance);
				logger.info("Instance is now running !");
			}
		}
	}

	public void start(String vmId) throws Exception {
		start(vmId, true);
	}

}
