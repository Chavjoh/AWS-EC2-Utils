package com.chavaillaz.awsec2utils.api.specification.arc.service;

import java.io.IOException;

/**
 * Service used to clone VM templates.
 * 
 * @author Johan Chavaillaz
 */
public interface CloneService_I {
	
	/**
	 * Clone a Virtual Machine Template file.
	 * 
	 * @param newVmId Clone ID
	 * @param vmId Virtual Machine Template ID to copy
	 * @throws IOException
	 */
	public void clone(String newVmId, String vmId) throws IOException;

}
