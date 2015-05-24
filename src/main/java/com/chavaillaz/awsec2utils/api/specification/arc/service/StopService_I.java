package com.chavaillaz.awsec2utils.api.specification.arc.service;

/**
 * Service used to stop VM.
 * 
 * @author Johan Chavaillaz
 */
public interface StopService_I {

	/**
	 * Stop a VM.
	 * 
	 * @param vmId Virtual Machine Template ID
	 * @throws Exception
	 */
	public void stop(String vmId) throws Exception;
	
	/**
	 * Stop a VM.
	 * 
	 * @param vmId Virtual Machine Template ID
	 * @param synchrone True to wait until the instance was stopped
	 * @throws Exception
	 */
	public void stop(String vmId, Boolean synchrone) throws Exception;
	
	/**
	 * Stop all VMs.
	 * 
	 * @throws Exception
	 */
	public void stopAll() throws Exception;
	
	/**
	 * Stop all VMs.
	 * 
	 * @param synchrone True to wait until the instance was stopped
	 * @throws Exception
	 */
	public void stopAll(Boolean synchrone) throws Exception;
	
}
