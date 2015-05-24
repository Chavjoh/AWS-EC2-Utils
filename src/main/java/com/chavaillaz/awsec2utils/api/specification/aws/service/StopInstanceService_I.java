package com.chavaillaz.awsec2utils.api.specification.aws.service;

import java.util.List;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.StopInstancesResult;

/**
 * Service used to stop instances.
 * 
 * @author Johan Chavaillaz
 */
public interface StopInstanceService_I {

	/**
	 * Stop an instance.
	 * 
	 * @param instance Instance to stop
	 * @return Stop instance result
	 */
	public StopInstancesResult stopInstance(Instance instance);
	
	/**
	 * Stop an instance.
	 * 
	 * @param instanceId Instance ID to stop
	 * @return Stop instance result
	 */
	public StopInstancesResult stopInstance(String instanceId);
	
	/**
	 * Stop a list of instances.
	 * 
	 * @param listInstance List of instances to stop
	 * @return Stop instance result
	 */
	public StopInstancesResult stopInstance(List<Instance> listInstance);
	
}
