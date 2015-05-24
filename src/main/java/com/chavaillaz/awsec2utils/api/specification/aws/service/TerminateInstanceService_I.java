package com.chavaillaz.awsec2utils.api.specification.aws.service;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.TerminateInstancesResult;

/**
 * Service used to terminate instances.
 * 
 * @author Johan Chavaillaz
 */
public interface TerminateInstanceService_I {
	
	/**
	 * Terminate an instance.
	 * 
	 * @param instance Instance to terminate
	 * @return Terminate instsance result
	 */
	public TerminateInstancesResult terminateInstance(Instance instance);
	
	/**
	 * Terminate an instance.
	 * 
	 * @param instanceId Instance ID to terminate
	 * @return Terminate instance result
	 */
	public TerminateInstancesResult terminateInstance(String instanceId);

}
