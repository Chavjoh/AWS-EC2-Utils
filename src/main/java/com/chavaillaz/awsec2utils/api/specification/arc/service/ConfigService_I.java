package com.chavaillaz.awsec2utils.api.specification.arc.service;

import java.io.IOException;

/**
 * Service used to configure AWS-EC2-Utils environment.
 * 
 * @author Johan Chavaillaz
 */
public interface ConfigService_I {
	
	/**
	 * Save configuration to access to Amazon Web Services and create a KeyPair.
	 * 
	 * @param accessKey Access Key given by Amazon
	 * @param secretAccessKey Secret Access Key given by Amazon
	 * @throws IOException
	 */
	public void config(String accessKey, String secretAccessKey) throws IOException;

}
