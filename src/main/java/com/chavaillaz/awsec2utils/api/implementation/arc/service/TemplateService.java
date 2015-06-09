package com.chavaillaz.awsec2utils.api.implementation.arc.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.SecurityGroup;
import com.amazonaws.services.ec2.model.Tag;
import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.aws.AwsService;
import com.chavaillaz.awsec2utils.api.implementation.common.AuthService_A;
import com.chavaillaz.awsec2utils.api.model.VmTemplate;
import com.chavaillaz.awsec2utils.api.specification.arc.service.TemplateService_I;
import com.chavaillaz.awsec2utils.api.specification.aws.service.SecurityGroupService_I;

/**
 * Implementation of {@link TemplateService_I}
 * 
 * @author Johan Chavaillaz
 */
public class TemplateService extends AuthService_A implements TemplateService_I {
	
	private static final Logger logger = LogManager.getLogger(TemplateService.class);

	public TemplateService(AmazonEC2Client aws) {
		super(aws);
	}

	public void define(String vmId, String imageId, String instanceType, String securityGroupName) throws Exception {
		SecurityGroup securityGroup = getSecurityGroup(securityGroupName);
		
		logger.info("Saving configuration");
		VmTemplate vmTemplate = new VmTemplate(vmId, imageId, instanceType, securityGroup.getGroupName());
		vmTemplate.save();
	}

	public void define(String vmId, String imageId, String instanceType) throws Exception {
		define(vmId, imageId, instanceType, Constants.AWS_SECURITY_GROUP);
	}

	public void purge(String vmId) throws Exception {
		VmTemplate template = VmTemplate.getTemplate(vmId);

		if (template == null) {
			logger.error("VM Template not found. Please create one with <define> command.");
			return;
		}
		
		logger.info("Getting instance state on Amazon ...");
		Instance instance = AwsService.getInstance().getDescribeInstanceService(aws).getFirstSignificantInstance(new Tag(Constants.TAG_KEY, vmId));
		
		if (instance != null) {
			logger.info("Terminating instance on Amazon ...");
			AwsService.getInstance().getTerminateInstanceService(aws).terminateInstance(instance);
		} else {
			logger.info("Instance is already terminated ...");
		}
		
		logger.info("Deleting template file ...");
		template.delete();
	}
	
	/**
	 * Get the security group defined or create and get the default security group.
	 * 
	 * @return SecurityGroup model class
	 */
	public SecurityGroup getSecurityGroup(String securityGroupName) {
		
		SecurityGroupService_I service = AwsService.getInstance().getSecurityGroupService(aws);
		
		try {
			logger.info("Trying to get the security group " + securityGroupName);
			return service.getSecurityGroup(securityGroupName);
		} 
		catch (Exception e) {
			logger.info("Security group does not exist");
			try {
				securityGroupName = Constants.AWS_SECURITY_GROUP;
				logger.info("Trying to get the default security group " + securityGroupName);
				return service.getSecurityGroup(securityGroupName);
			} catch (Exception e2) {
				logger.info("Security group doesn't exist");
				logger.info("Creating default security group");
				service.createSecurityGroupWithSshPermission(securityGroupName, Constants.AWS_SECURITY_GROUP_DESCRIPTION);
				return service.getSecurityGroup(securityGroupName);
			}
		}
		
	}

}
