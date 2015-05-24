package com.chavaillaz.awsec2utils.api.specification.arc.service;

/**
 * Service used to reboot VMs.
 * 
 * @author Johan Chavaillaz
 */
public interface RebootService_I {
	
	/**
	 * Reboot a VM.
	 * 
	 * @param vmId Virtual Machine Template ID
	 * @throws Exception
	 */
	public void reboot(String vmId) throws Exception;
	
	/**
	 * Reboot a VM.
	 * 
	 * @param vmId Virtual Machine Template ID
	 * @param synchrone True to wait until the instance was running
	 * @throws Exception
	 */
	public void reboot(String vmId, Boolean synchrone) throws Exception;

}
