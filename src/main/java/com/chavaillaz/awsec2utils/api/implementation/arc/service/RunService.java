package com.chavaillaz.awsec2utils.api.implementation.arc.service;

import java.io.File;
import java.util.concurrent.TimeUnit;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.xfer.FileSystemFile;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.chavaillaz.awsec2utils.api.implementation.arc.ArcService;
import com.chavaillaz.awsec2utils.api.implementation.common.AuthService_A;
import com.chavaillaz.awsec2utils.api.specification.arc.service.RunService_I;

/**
 * Implementation of {@link RunService_I}
 * 
 * @author Johan Chavaillaz
 */
public class RunService extends AuthService_A implements RunService_I {
	
	private static final Logger logger = LogManager.getLogger(RunService.class);
	private static final String CHMOD = "chmod +x ";
	private static final String ROOT = "root";
	private static final String RUN = "run";
	private static final String SH = "sh";

	public RunService(AmazonEC2Client aws) {
		super(aws);
	}

	public void run(String vmId, String source, String destination, String username) throws Exception {
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
			
			File file = new File(source);
			
			logger.info("Running command files ...");
			if (file.isDirectory()) {
				for (final File fileEntry : file.listFiles()) {
					String extension = FilenameUtils.getExtension(fileEntry.getName());
					if (extension.equals(SH) || extension.equals(RUN)) {
						String fileDestination = destination + File.separator + fileEntry.getName();
						runCommand(ssh, CHMOD + fileDestination);
						runCommand(ssh, fileDestination);
					}
			    }
			} else {
				String fileDestination = destination + File.separator + file.getName();
				runCommand(ssh, CHMOD + fileDestination);
				runCommand(ssh, fileDestination);
			}
		} finally {
			if (ssh != null) {
				logger.info("Disconnecting ...");
				ssh.disconnect();
				ssh.close();
			}
		}
	}
	
	public void run(String vmId, String source, String destination) throws Exception {
		run(vmId, source, destination, ROOT);
	}
	
	public void runCommand(SSHClient ssh, String command) throws Exception {
		final Session session = ssh.startSession();
		
		try {
			final Command cmd = session.exec(command);
			System.out.println(IOUtils.readFully(cmd.getInputStream()).toString());
			cmd.join(30, TimeUnit.SECONDS);
		} finally {
			session.close();
		}
	}

}
