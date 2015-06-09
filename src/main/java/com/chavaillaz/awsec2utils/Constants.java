package com.chavaillaz.awsec2utils;

/**
 * Constants of AWS-EC2-Utils project.
 * 
 * @author Johan Chavaillaz
 */
public class Constants {
	
	public static final String PROPERTIES_EXTENSION = ".properties";
	
	public static final String KEY_PAIR_PREFIX = "AWS-EC2-Utils-";
	public static final String KEY_PAIR_EXTENSION = ".pem";
	
	public static final String PREFIX = "vm ";
	
	public static final String AWS_ENDPOINT = "ec2.us-east-1.amazonaws.com";
	public static final String AWS_SECURITY_GROUP = "AWS-EC2-Utils";
	public static final String AWS_SECURITY_GROUP_DESCRIPTION = "Default group used by AWS-EC2-Utils.";
	
	public static final String VM_FOLDER = "template/";
	public static final String VM_EXTENSION = PROPERTIES_EXTENSION;
	
	public static final String TAG_KEY = "AWS-EC2-Utils-Key";
	public static final String TAG_GROUP_KEY = "AWS-EC2-Utils-Group";

}
