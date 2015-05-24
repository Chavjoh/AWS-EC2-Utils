package com.chavaillaz.awsec2utils.api.specification.aws.service;

import com.amazonaws.services.ec2.model.KeyPair;

/**
 * Service used to manage KeyPair.
 * 
 * @author Johan Chavaillaz
 */
public interface KeyPairService_I {

	/**
	 * Create a new KeyPair used to create and access virtual machine.
	 * 
	 * @param keyPairName KeyPair name
	 * @return KeyPair created
	 */
	public KeyPair createKeyPair(String keyPairName);
	
}
