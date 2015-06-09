package com.chavaillaz.awsec2utils.api.implementation.arc.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;
import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.aws.AwsService;
import com.chavaillaz.awsec2utils.api.implementation.common.AuthService_A;
import com.chavaillaz.awsec2utils.api.model.Configuration;
import com.chavaillaz.awsec2utils.api.specification.arc.service.SshService_I;
import com.chavaillaz.awsec2utils.utils.VmState;

/**
 * Implementation of {@link SshService_I}
 * 
 * @author Johan Chavaillaz
 */
public class SshService extends AuthService_A implements SshService_I {

	private static final Logger logger = LogManager.getLogger(SshService.class);
	
	public SshService(AmazonEC2Client aws) {
		super(aws);
	}

	public SSHClient getSshClient(String vmId, String username) throws Exception {
		logger.info("Getting configuration ...");
		Configuration configuration = Configuration.getConfiguration();

		logger.info("Getting instance information ...");
		Instance instance = AwsService.getInstance()
				.getDescribeInstanceService(aws)
				.getFirstSignificantInstance(new Tag(Constants.TAG_KEY, vmId));

		if (instance == null || !VmState.isRunning(instance)) {
			logger.error("Instance is not running ...");
			return null;
		} 
		
		logger.info("Creating a SSH connexion ...");
		final SSHClient ssh = new SSHClient();
		ssh.addHostKeyVerifier(new PromiscuousVerifier());
		ssh.connect(instance.getPublicDnsName());

		
		logger.info("Authentication with KeyPair ...");
		ssh.authPublickey(username, configuration.getKeyPairPath());
		
		return ssh;
	}

}
