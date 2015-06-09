package com.chavaillaz.awsec2utils.api.implementation.arc.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;
import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.aws.AwsService;
import com.chavaillaz.awsec2utils.api.implementation.common.AuthService_A;
import com.chavaillaz.awsec2utils.api.model.VmTemplate;
import com.chavaillaz.awsec2utils.api.specification.arc.service.RebootService_I;
import com.chavaillaz.awsec2utils.utils.VmState;

/**
 * Implementation of {@link RebootService_I}
 * 
 * @author Johan Chavaillaz
 */
public class RebootService extends AuthService_A implements RebootService_I {
	
	private static final Logger logger = LogManager.getLogger(RebootService.class);

	public RebootService(AmazonEC2Client aws) {
		super(aws);
	}

	public void reboot(String vmId, Boolean synchrone) throws Exception {
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
			logger.info("Rebooting instance ...");
			
			AwsService.getInstance().getRebootInstanceService(aws).reboot(instance);
			
			if (synchrone) {
				logger.info("Waiting for the start of the instance ...");
				AwsService.getInstance().getWaitInstanceService(aws).waitInstance(instance);
				logger.info("Instance is now running !");
			}
		} 
		else {
			logger.error("Instance must be in running state to reboot.");
		}
	}

	public void reboot(String vmId) throws Exception {
		reboot(vmId, true);
	}

}
