package com.chavaillaz.awsec2utils.api.specification.arc.service;

/**
 * Service used to manage VM templates.
 * 
 * @author Johan Chavaillaz
 */
public interface TemplateService_I {

	/**
	 * Define a template file to manage a virtual machine.
	 * Create if necessary the security group and save template file.
	 * 
	 * @param vmId Virtual Machine Template ID
	 * @param imageId Amazon Image ID
	 * @param instanceType Instance type
	 * @param securityGroup Security group name
	 * @throws Exception
	 */
	public void define(String vmId, String imageId, String instanceType, String securityGroup) throws Exception;
	
	/**
	 * Define a template file to manage a virtual machine.
	 * Create if necessary the default security group and save template file.
	 * 
	 * @param vmId Virtual Machine Template ID
	 * @param imageId Amazon Image ID
	 * @param instanceType Instance type
	 * @throws Exception
	 */
	public void define(String vmId, String imageId, String instanceType) throws Exception;
	
	/**
	 * Delete VM template and terminate the corresponding instance on Amazon EC2.
	 * 
	 * @param vmId Virtual Machine Template ID
	 * @throws Exception
	 */
	public void purge(String vmId) throws Exception;
	
}
