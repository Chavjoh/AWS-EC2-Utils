package com.chavaillaz.awsec2utils.api.specification.arc.service;

/**
 * Service used to run executable files on VMs.
 * 
 * @author Johan Chavaillaz
 */
public interface RunService_I {

	/**
	 * Run executables files from source folder in destination folder on VM.
	 * 
	 * @param vmId Virtual Machine Template ID
	 * @param source Source folder containing executable files or executable file
	 * @param destination Destination folder where to store executables files on VM
	 * @param username Username used to access via SSH to the VM
	 * @throws Exception
	 */
	public void run(String vmId, String source, String destination, String username) throws Exception;

	/**
	 * Run executables files from source folder in destination folder on VM.
	 * 
	 * @param vmId Virtual Machine Template ID
	 * @param source Source folder containing executable files or executable file
	 * @param destination Destination folder where to store executables files on VM
	 * @throws Exception
	 */
	public void run(String vmId, String source, String destination) throws Exception;
	
}
