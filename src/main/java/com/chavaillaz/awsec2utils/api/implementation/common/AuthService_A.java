package com.chavaillaz.awsec2utils.api.implementation.common;

import com.amazonaws.services.ec2.AmazonEC2Client;

/**
 * Abstract class representing authenticated service.
 * Makes available {@link AmazonEC2Client} as protected attribute.
 * 
 * @author Johan Chavaillaz
 */
public abstract class AuthService_A {
	
	protected AmazonEC2Client aws;
	
	public AuthService_A(AmazonEC2Client aws) {
		this.aws = aws;
	}

}
