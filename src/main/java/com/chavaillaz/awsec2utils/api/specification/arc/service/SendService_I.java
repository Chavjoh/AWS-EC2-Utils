package com.chavaillaz.awsec2utils.api.specification.arc.service;

/**
 * Service used to send files or folder to VM.
 * 
 * @author Johan Chavaillaz
 */
public interface SendService_I {

	/**
	 * Copy source file or folder to the destination folder on VM.
	 * 
	 * @param vmId Virtual Machine Template ID
	 * @param source Source folder containing executable files or executable file
	 * @param destination Destination folder where to store executables files on VM
	 * @param username Username used to access via SSH to the VM
	 * @throws Exception
	 */
	public void send(String vmId, String source, String destination, String username) throws Exception;
	
	/**
	 * Copy source file or folder to the destination folder on VM.
	 * 
	 * @param vmId Virtual Machine Template ID
	 * @param source Source folder containing executable files or executable file
	 * @param destination Destination folder where to store executables files on VM
	 * @throws Exception
	 */
	public void send(String vmId, String source, String destination) throws Exception;
	
}
