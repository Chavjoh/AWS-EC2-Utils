package com.chavaillaz.awsec2utils.use.command;

import java.util.List;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.chavaillaz.awsec2utils.api.implementation.aws.AwsService;
import com.chavaillaz.awsec2utils.use.exception.CommandParametersException;

/**
 * Authenticated command.
 * Get the AWS EC2 service with user credentials.
 * 
 * @author Johan Chavaillaz
 */
public abstract class CommandAuth extends Command_A {
	
	protected AmazonEC2Client aws;

	/**
	 * Create a new authenticated command.
	 * Get the AWS EC2 service with user credentials.
	 * 
	 * @param parameters Parameters used to configure the command
	 * @throws CommandParametersException
	 */
	public CommandAuth(List<String> parameters) throws CommandParametersException {
		super(parameters);

		try {
			aws = AwsService.getInstance().getAmazonClientService().getClient();
		} catch (Exception e) {
			throw new CommandParametersException(this, "You need to define your credientials with config command.");
		}
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
