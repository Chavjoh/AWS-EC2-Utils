package com.chavaillaz.awsec2utils.api.specification.aws.service;

import java.util.List;

import com.amazonaws.services.ec2.model.Instance;

/**
 * Service used to list instances.
 * 
 * @author Johan Chavaillaz
 */
public interface ListInstanceService_I {
	
	/**
	 * Get all instances on Amazon EC2.
	 * 
	 * @return List of all instances
	 * @throws Exception
	 */
	public List<Instance> getListInstance() throws Exception;
	
}
