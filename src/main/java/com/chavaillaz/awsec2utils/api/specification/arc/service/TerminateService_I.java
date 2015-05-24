package com.chavaillaz.awsec2utils.api.specification.arc.service;

/**
 * Service used to terminate VM.
 * 
 * @author Johan Chavaillaz
 */
public interface TerminateService_I {
	
	/**
	 * Terminate a VM.
	 * 
	 * @param vmId Virtual Machine Template ID
	 * @throws Exception
	 */
	public void terminate(String vmId) throws Exception;
	
	/**
	 * Terminate a VM.
	 * 
	 * @param vmId Virtual Machine Template ID
	 * @param synchrone True to wait until the instance was terminated
	 * @throws Exception
	 */
	public void terminate(String vmId, Boolean synchrone) throws Exception;
	
	/**
	 * Terminate all VMs.
	 * 
	 * @throws Exception
	 */
	public void terminateAll() throws Exception;
	
	/**
	 * Terminate all VMs.
	 * 
	 * @param synchrone True to wait until the instance was terminated
	 * @throws Exception
	 */
	public void terminateAll(Boolean synchrone) throws Exception;

}
