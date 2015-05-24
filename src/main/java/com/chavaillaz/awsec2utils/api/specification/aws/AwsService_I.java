package com.chavaillaz.awsec2utils.api.specification.aws;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.chavaillaz.awsec2utils.api.specification.aws.service.AmazonClientService_I;
import com.chavaillaz.awsec2utils.api.specification.aws.service.DescribeInstanceService_I;
import com.chavaillaz.awsec2utils.api.specification.aws.service.KeyPairService_I;
import com.chavaillaz.awsec2utils.api.specification.aws.service.ListInstanceService_I;
import com.chavaillaz.awsec2utils.api.specification.aws.service.RebootInstanceService_I;
import com.chavaillaz.awsec2utils.api.specification.aws.service.SecurityGroupService_I;
import com.chavaillaz.awsec2utils.api.specification.aws.service.StartInstanceService_I;
import com.chavaillaz.awsec2utils.api.specification.aws.service.StopInstanceService_I;
import com.chavaillaz.awsec2utils.api.specification.aws.service.TagInstanceService_I;
import com.chavaillaz.awsec2utils.api.specification.aws.service.TerminateInstanceService_I;
import com.chavaillaz.awsec2utils.api.specification.aws.service.WaitInstanceService_I;

/**
 * Services used in to simplify calling to AWS EC2 SDK functions.
 * 
 * @author Johan Chavaillaz
 */
public interface AwsService_I {
	
	/**
	 * @return A new amazon client service
	 */
	public AmazonClientService_I getAmazonClientService();
	
	/**
	 * @param aws Amazon EC2 client
	 * @return A new describe instance service
	 */
	public DescribeInstanceService_I getDescribeInstanceService(AmazonEC2Client aws);
	
	/**
	 * @param aws Amazon EC2 client
	 * @return A new key pair service
	 */
	public KeyPairService_I getKeyPairService(AmazonEC2Client aws);

	/**
	 * @param aws Amazon EC2 client
	 * @return A new list instance service
	 */
	public ListInstanceService_I getListInstanceService(AmazonEC2Client aws);

	/**
	 * @param aws Amazon EC2 client
	 * @return A new security group service
	 */
	public SecurityGroupService_I getSecurityGroupService(AmazonEC2Client aws);

	/**
	 * @param aws Amazon EC2 client
	 * @return A new start instance service
	 */
	public StartInstanceService_I getStartInstanceService(AmazonEC2Client aws);

	/**
	 * @param aws Amazon EC2 client
	 * @return A new stop instance service
	 */
	public StopInstanceService_I getStopInstanceService(AmazonEC2Client aws);

	/**
	 * @param aws Amazon EC2 client
	 * @return A new tag instance service
	 */
	public TagInstanceService_I getTagInstanceService(AmazonEC2Client aws);

	/**
	 * @param aws Amazon EC2 client
	 * @return A new reboot instance service
	 */
	public RebootInstanceService_I getRebootInstanceService(AmazonEC2Client aws);

	/**
	 * @param aws Amazon EC2 client
	 * @return A new terminate instance service
	 */
	public TerminateInstanceService_I getTerminateInstanceService(AmazonEC2Client aws);

	/**
	 * @param aws Amazon EC2 client
	 * @return A new wait instance service
	 */
	public WaitInstanceService_I getWaitInstanceService(AmazonEC2Client aws);
	
}
