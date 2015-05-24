package com.chavaillaz.awsec2utils.api.specification.aws.service;

import java.util.List;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;

/**
 * Service used to get information about instances.
 * 
 * @author Johan Chavaillaz
 */
public interface DescribeInstanceService_I {
	
	/**
	 * Get first instance with a specific tag.
	 * 
	 * @param tag Tag to search
	 * @return First instance found with the indicated tag
	 */
	public Instance getFirstInstance(Tag tag);
	
	/**
	 * Get all instances with a specific tag.
	 * 
	 * @param tag Tag to search
	 * @return List of all instances with the indicated tag
	 */
	public List<Instance> getInstance(Tag tag);
	
	/**
	 * Get an instance with indicated instance ID.
	 * 
	 * @param instanceId Instance ID
	 * @return Instance model
	 */
	public Instance getInstance(String instanceId);

}
