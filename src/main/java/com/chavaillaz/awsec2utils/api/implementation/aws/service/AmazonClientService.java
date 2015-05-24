package com.chavaillaz.awsec2utils.api.implementation.aws.service;

import com.amazonaws.auth.AWSCredentials;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.model.Configuration;
import com.chavaillaz.awsec2utils.api.specification.aws.service.AmazonClientService_I;

/**
 * Implementation of {@link AmazonClientService_I}
 * 
 * @author Johan Chavaillaz
 */
public class AmazonClientService implements AmazonClientService_I {

	public AmazonEC2Client getClient() throws Exception {
		Configuration configuration = Configuration.getConfiguration();
		return getClient(configuration.getAccessKey(), configuration.getPrivateAccessKey());
	}

	public AmazonEC2Client getClient(String accessKey, String privateAccessKey) {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, privateAccessKey);
		AmazonEC2Client amazonEC2Client = new AmazonEC2Client(credentials);
		amazonEC2Client.setEndpoint(Constants.AWS_ENDPOINT);
		
		return amazonEC2Client;
	}

}
