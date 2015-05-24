package com.chavaillaz.awsec2utils.api.specification.aws.service;

import java.util.List;

import com.amazonaws.services.ec2.model.Instance;

/**
 * Service used to wait instances state change.
 * 
 * @author Johan Chavaillaz
 */
public interface WaitInstanceService_I {

	/**
	 * Wait until all indicated instances are in final indicated state.
	 * 
	 * @param listInstance List of instances to wait
	 * @param finalState Final state to wait for each instance
	 * @throws InterruptedException
	 */
	public void waitInstance(List<Instance> listInstance, String finalState) throws InterruptedException;
	
	/**
	 * Wait until all indicated instances are in running state.
	 * 
	 * @param listInstance List of instances to wait
	 * @throws InterruptedException
	 */
	public void waitInstance(List<Instance> listInstance) throws InterruptedException;
	
	/**
	 * Wait until the instance is in final indicated state.
	 * 
	 * @param instance Instance to wait
	 * @param finalState Final state to wait for the instance
	 * @throws InterruptedException
	 */
	public void waitInstance(Instance instance, String finalState) throws InterruptedException;
	
	/**
	 * Wait until the instance is in running state.
	 * 
	 * @param instance Instance to wait
	 * @throws InterruptedException
	 */
	public void waitInstance(Instance instance) throws InterruptedException;
	
	/**
	 * Wait until the instance is in running state.
	 * 
	 * @param instanceId ID of the instance to wait
	 * @throws InterruptedException
	 */
	public void waitInstance(String instanceId) throws InterruptedException;
	
	/**
	 * Wait until the instance is in indicated state.
	 * 
	 * @param instanceId ID of the instance to wait
	 * @param finalState Final state to wait for the instance
	 * @throws InterruptedException
	 */
	public void waitInstance(String instanceId, String finalState) throws InterruptedException;
	
}
