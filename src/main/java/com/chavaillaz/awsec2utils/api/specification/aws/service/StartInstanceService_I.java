package com.chavaillaz.awsec2utils.api.specification.aws.service;

import java.io.IOException;
import java.util.List;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.StartInstancesResult;

/**
 * Service used to start instances.
 * 
 * @author Johan Chavaillaz
 */
public interface StartInstanceService_I {
	
	/**
	 * Start a stopped instance.
	 * 
	 * @param instance Instance model
	 * @return Result
	 */
	public StartInstancesResult startInstance(Instance instance);
	
	/**
	 * Start a stopped instance.
	 * 
	 * @param instanceId Instance ID
	 * @return Result
	 */
	public StartInstancesResult startInstance(String instanceId);
	
	/**
	 * Start a list of stopped instances.
	 * 
	 * @param listInstance List of stopped instances
	 * @return Result
	 */
	public StartInstancesResult startInstance(List<Instance> listInstance);
	
	/**
	 * Run a new instance that does not exist presently on EC2.
	 * 
	 * @param imageId Amazon Image ID
	 * @param instanceType Instance type
	 * @param keyName KeyPair name
	 * @param securityGroup Security group name
	 * @return Running instances
	 * @throws IOException
	 */
	public List<Instance> runInstance(String imageId, String instanceType, String keyName, String securityGroup) throws IOException;

}
