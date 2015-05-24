package com.chavaillaz.awsec2utils.api.specification.aws.service;

import com.amazonaws.services.ec2.model.Instance;

/**
 * Service used to reboot instances.
 * 
 * @author Johan Chavaillaz
 */
public interface RebootInstanceService_I {
	
	/**
	 * Reboot an instance.
	 * 
	 * @param instance Instance to reboot
	 */
	public void reboot(Instance instance);
	
	/**
	 * Reboot an instance.
	 * 
	 * @param instanceId ID of the instance to reboot
	 */
	public void reboot(String instanceId);

}
