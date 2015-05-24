package com.chavaillaz.awsec2utils.api.specification.arc.service;

import java.util.List;

import com.chavaillaz.awsec2utils.api.model.VmTemplate;

/**
 * Service used to list existing templates.
 * 
 * @author Johan Chavaillaz
 */
public interface ListService_I {
	
	/**
	 * List all templates.
	 * 
	 * @return List of all templates
	 * @throws Exception
	 */
	public List<VmTemplate> list() throws Exception;
	
	/**
	 * List all templates with state indication.
	 * 
	 * @return List of all templates with state indication
	 * @throws Exception
	 */
	public List<VmTemplate> listWithState() throws Exception;
	
	/**
	 * List all templates in a specific state.
	 * 
	 * @param state Desired state of VM to retrieve
	 * @return List of all templates in the corresponding state
	 * @throws Exception
	 */
	public List<VmTemplate> listState(String state) throws Exception;

}
