package com.chavaillaz.awsec2utils.api.specification.aws.service;

import java.util.List;

import com.amazonaws.services.ec2.model.SecurityGroup;

/**
 * Service used to manage security groups.
 * 
 * @author Johan Chavaillaz
 */
public interface SecurityGroupService_I {
	
	/**
	 * Return the list of all existing security groups.
	 * 
	 * @return List of all security groups
	 */
	public List<SecurityGroup> listSecurityGroup();
	
	/**
	 * Return the security group with the indicated name.
	 * 
	 * @param securityGroupName Security group name
	 * @return Security group model
	 */
	public SecurityGroup getSecurityGroup(String securityGroupName);
	
	/**
	 * Create a new security group with SSH permission only (in/out).
	 * 
	 * @param securityGroupName Security group name to create
	 * @param securityGroupDescription Security group description
	 */
	public void createSecurityGroupWithSshPermission(String securityGroupName, String securityGroupDescription);

}
