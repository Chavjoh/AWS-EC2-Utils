package com.chavaillaz.awsec2utils.api.implementation.aws.service;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.CreateKeyPairResult;
import com.amazonaws.services.ec2.model.KeyPair;
import com.chavaillaz.awsec2utils.api.implementation.common.AuthService_A;
import com.chavaillaz.awsec2utils.api.specification.aws.service.KeyPairService_I;

/**
 * Implementation of {@link KeyPairService_I}
 * 
 * @author Johan Chavaillaz
 */
public class KeyPairService extends AuthService_A implements KeyPairService_I {

	public KeyPairService(AmazonEC2Client aws) {
		super(aws);
	}
	
	public KeyPair createKeyPair(String keyPairName) {
		CreateKeyPairRequest createKeyPairRequest = new CreateKeyPairRequest();
		createKeyPairRequest.withKeyName(keyPairName);

		CreateKeyPairResult createKeyPairResult = aws.createKeyPair(createKeyPairRequest);
		return createKeyPairResult.getKeyPair();
	}

}
