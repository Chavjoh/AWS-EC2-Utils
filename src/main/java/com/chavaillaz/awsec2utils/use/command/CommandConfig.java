package com.chavaillaz.awsec2utils.use.command;

import java.io.IOException;
import java.util.List;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.arc.ArcService;
import com.chavaillaz.awsec2utils.api.implementation.aws.AwsService;
import com.chavaillaz.awsec2utils.use.exception.CommandParametersException;

/**
 * Run the configuration command. 
 * Create a new KeyPair and save configuration.
 * 
 * @author Johan Chavaillaz
 */
public class CommandConfig extends Command_A {
	
	public static final String KEY = "config";
	public static String HELP = Constants.PREFIX + KEY + " aws_access_key aws_private_key";
	
	private AmazonEC2Client aws;
	private String accessKey;
	private String secretAccessKey;
	
	public CommandConfig(List<String> parameters) throws Exception {
		super(parameters);

		if (parameters.size() == 2) {
			accessKey = parameters.get(0);
			secretAccessKey = parameters.get(1);
			aws = AwsService.getInstance().getAmazonClientService().getClient(accessKey, secretAccessKey);
		} else {
			throw new CommandParametersException(this, "Invalid parameters.");
		}
	}
	
	@Override
	public void run() throws IOException {
		ArcService.getInstance().getConfigService(aws).config(secretAccessKey, secretAccessKey);
	}

	/**
	 * Shutdown AWS services.
	 */
	@Override
	public void clean() {
		if (aws != null) {
			aws.shutdown();
		}
	}

}
