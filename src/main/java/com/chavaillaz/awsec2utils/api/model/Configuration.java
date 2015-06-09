package com.chavaillaz.awsec2utils.api.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;

import com.chavaillaz.awsec2utils.Constants;

/**
 * AWS-EC2-Utils configuration management.
 * 
 * @author Johan Chavaillaz
 */
public class Configuration {

	public static final String KEYPAIR_PATH_KEY = "keyPairPath";
	public static final String ACCESS_KEY = "accessKey";
	public static final String PRIVATE_ACCESS_KEY = "secretAccessKey";
	public static final String CONFIG_FILE = "config" + Constants.PROPERTIES_EXTENSION;
	public static final String GROUP_KEY = "group";

	private Properties properties;
	private String accessKey;
	private String secretAccessKey;
	private String keyPairPath;
	private String group;
	
	public Configuration(String accessKey, String secretAccessKey, String keyPairPath, String group) {
		super();
		
		this.properties = new Properties();
		this.accessKey = accessKey;
		this.secretAccessKey = secretAccessKey;
		this.keyPairPath = keyPairPath;
		this.group = group;
	}

	/**
	 * Get configuration from properties file.
	 * 
	 * @return Configuration instance
	 * @throws IOException
	 */
	public static Configuration getConfiguration() throws IOException {
		Properties properties = new Properties();
		InputStream input = null;

		input = new FileInputStream(CONFIG_FILE);
		properties.load(input);

		String accessKey = properties.getProperty(ACCESS_KEY);
		String privateAccessKey = properties.getProperty(PRIVATE_ACCESS_KEY);
		String keyPairName = properties.getProperty(KEYPAIR_PATH_KEY);
		String group = properties.getProperty(GROUP_KEY);

		input.close();
		
		return new Configuration(accessKey, privateAccessKey, keyPairName, group);
	}
	
	/**
	 * Save configuration in properties file.
	 * 
	 * @throws IOException
	 */
	public void save() throws IOException {
		OutputStream output = new FileOutputStream(CONFIG_FILE);
		properties.setProperty(ACCESS_KEY, accessKey);
		properties.setProperty(PRIVATE_ACCESS_KEY, secretAccessKey);
		properties.setProperty(KEYPAIR_PATH_KEY, keyPairPath);
		properties.setProperty(GROUP_KEY, group);
		properties.store(output, null);
		output.close();
	}
	
	public String getAccessKey() {
		return accessKey;
	}
	
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	
	public String getPrivateAccessKey() {
		return secretAccessKey;
	}
	
	public void setPrivateAccessKey(String privateAccessKey) {
		this.secretAccessKey = privateAccessKey;
	}
	
	public String getKeyPairPath() {
		return keyPairPath;
	}
	
	public void setKeyPairPath(String keyPairName) {
		this.keyPairPath = keyPairName;
	}
	
	public String getKeyPairName() {
		return FilenameUtils.getBaseName(getKeyPairPath());
	}
	
	public void setGroup(String group) {
		this.group = group;
	}
	
	public String getGroup() {
		return this.group;
	}
	
}
