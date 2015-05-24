package com.chavaillaz.awsec2utils.api.specification.aws.service;

import com.amazonaws.services.ec2.AmazonEC2Client;

/**
 * Service used to manage Amazon SDK Client.
 * 
 * @author Johan Chavaillaz
 */
public interface AmazonClientService_I {

	/**
	 * Create a new Amazon Client for EC2 service.
	 * 
	 * @return Amazon EC2 Client
	 * @throws Exception
	 */
	public AmazonEC2Client getClient() throws Exception;
	
	/**
	 * Create a new Amazon Client for EC2 service.
	 * 
	 * @param accessKey Access Key given by Amazon
	 * @param privateAccessKey Private Access Key given by Amazon
	 * @return Amazon EC2 Client
	 */
	public AmazonEC2Client getClient(String accessKey, String privateAccessKey);
	
}
