package com.chavaillaz.awsec2utils.api.specification.arc.service;

/**
 * Service used to start VM.
 * 
 * @author Johan Chavaillaz
 */
public interface StartService_I {
	
	/**
	 * Start a VM.
	 * 
	 * @param vmId Virtual Machine Template ID
	 * @throws Exception
	 */
	public void start(String vmId) throws Exception;
	
	/**
	 * Start a VM.
	 * 
	 * @param vmId Virtual Machine Template ID
	 * @param synchrone True to wait until the instance was running
	 * @throws Exception
	 */
	public void start(String vmId, Boolean synchrone) throws Exception;

}
