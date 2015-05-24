package com.chavaillaz.awsec2utils.api.implementation.arc.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.xfer.FileSystemFile;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.chavaillaz.awsec2utils.api.implementation.arc.ArcService;
import com.chavaillaz.awsec2utils.api.implementation.common.AuthService_A;
import com.chavaillaz.awsec2utils.api.specification.arc.service.SendService_I;

/**
 * Implementation of {@link SendService_I}
 * 
 * @author Johan Chavaillaz
 */
public class SendService extends AuthService_A implements SendService_I {

	private static final Logger logger = LogManager.getLogger(SendService.class);
	private static final String ROOT = "root";

	public SendService(AmazonEC2Client aws) {
		super(aws);
	}

	public void send(String vmId, String source, String destination, String username) throws Exception {
		SSHClient ssh = null;

		try {
			ssh = ArcService.getInstance().getSshService(aws).getSshClient(vmId, username);
			
			final SFTPClient sftp = ssh.newSFTPClient();
			try {
				logger.info("Uploading file(s) with SFTP client ...");
				sftp.put(new FileSystemFile(source), destination);
			} finally {
				sftp.close();
			}
		} finally {
			if (ssh != null) {
				logger.info("Disconnecting ...");
				ssh.disconnect();
				ssh.close();
			}
		}
	}

	public void send(String vmId, String source, String destination) throws Exception {
		send(vmId, source, destination, ROOT);
	}

}
