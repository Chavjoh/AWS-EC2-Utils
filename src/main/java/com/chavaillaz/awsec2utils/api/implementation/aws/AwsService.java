package com.chavaillaz.awsec2utils.api.implementation.aws;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.chavaillaz.awsec2utils.api.implementation.aws.service.*;
import com.chavaillaz.awsec2utils.api.specification.aws.AwsService_I;
import com.chavaillaz.awsec2utils.api.specification.aws.service.*;

/**
 * Implementation of {@link AwsService_I}
 * 
 * @author Johan Chavaillaz
 */
public class AwsService implements AwsService_I {

	public static AwsService instance;
	
	public static AwsService_I getInstance() {
		if (instance == null) {
			instance = new AwsService();
		}
		return instance;
	}

	public AmazonClientService_I getAmazonClientService() {
		return new AmazonClientService();
	}

	public DescribeInstanceService_I getDescribeInstanceService(AmazonEC2Client aws) {
		return new DescribeInstanceService(aws);
	}

	public KeyPairService_I getKeyPairService(AmazonEC2Client aws) {
		return new KeyPairService(aws);
	}

	public ListInstanceService_I getListInstanceService(AmazonEC2Client aws) {
		return new ListInstanceService(aws);
	}

	public SecurityGroupService_I getSecurityGroupService(AmazonEC2Client aws) {
		return new SecurityGroupService(aws);
	}

	public StartInstanceService_I getStartInstanceService(AmazonEC2Client aws) {
		return new StartInstanceService(aws);
	}

	public StopInstanceService_I getStopInstanceService(AmazonEC2Client aws) {
		return new StopInstanceService(aws);
	}

	public TagInstanceService_I getTagInstanceService(AmazonEC2Client aws) {
		return new TagInstanceService(aws);
	}

	public TerminateInstanceService_I getTerminateInstanceService(AmazonEC2Client aws) {
		return new TerminateInstanceService(aws);
	}

	public WaitInstanceService_I getWaitInstanceService(AmazonEC2Client aws) {
		return new WaitInstanceService(aws);
	}

	public RebootInstanceService_I getRebootInstanceService(AmazonEC2Client aws) {
		return new RebootInstanceService(aws);
	}
	
}
