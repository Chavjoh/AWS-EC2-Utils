package com.chavaillaz.awsec2utils.api.implementation.arc.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.KeyPair;
import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.aws.AwsService;
import com.chavaillaz.awsec2utils.api.implementation.common.AuthService_A;
import com.chavaillaz.awsec2utils.api.model.Configuration;
import com.chavaillaz.awsec2utils.api.specification.arc.service.ConfigService_I;

/**
 * Implementation of {@link ConfigService_I}
 * 
 * @author Johan Chavaillaz
 */
public class ConfigService extends AuthService_A implements ConfigService_I {
	
	private static final Logger logger = LogManager.getLogger(ConfigService.class);
	
	public ConfigService(AmazonEC2Client aws) {
		super(aws);
	}

	public void config(String accessKey, String secretAccessKey) throws IOException {
		logger.info("Creating a new KeyPair");
		String group = UUID.randomUUID().toString();
		String keyPairName = Constants.KEY_PAIR_PREFIX + UUID.randomUUID();
		String keyPairPath = keyPairName + Constants.KEY_PAIR_EXTENSION;
		
		KeyPair keyPair = AwsService.getInstance().getKeyPairService(aws).createKeyPair(keyPairName);
		PrintWriter out = new PrintWriter(keyPairPath);
		out.print(keyPair.getKeyMaterial());
		out.close();
		
		logger.info("Saving configuration");
		Configuration configuration = new Configuration(accessKey, secretAccessKey, keyPairPath, group);
		configuration.save();
	}

}
