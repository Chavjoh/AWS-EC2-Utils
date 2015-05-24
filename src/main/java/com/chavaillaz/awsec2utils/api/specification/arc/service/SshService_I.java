package com.chavaillaz.awsec2utils.api.specification.arc.service;

import net.schmizz.sshj.SSHClient;

/**
 * Service used to manage SSH communication to VM.
 * 
 * @author Johan Chavaillaz
 */
public interface SshService_I {
	
	/**
	 * Return a new SSH Client connection to a VM.
	 * 
	 * @param vmId Virtual Machine Template ID
	 * @param username Username used to access via SSH to the VM
	 * @return New SSH Client connection
	 * @throws Exception
	 */
	public SSHClient getSshClient(String vmId, String username) throws Exception;

}
