package com.chavaillaz.awsec2utils.api.specification.aws.service;

import java.util.List;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;

/**
 * Service used to tag instances.
 * 
 * @author Johan Chavaillaz
 */
public interface TagInstanceService_I {

	/**
	 * Tag multiples instances with a single tag.
	 * 
	 * @param instances List of all instances to tag
	 * @param tag Tag to add to instances
	 */
	public void tagInstance(List<Instance> instances, Tag tag);
	
	/**
	 * Tag multiples instances with multiples tags.
	 * 
	 * @param instances List of all instances to tag
	 * @param listTag List of tags to add to instances
	 */
	public void tagInstance(List<Instance> instances, List<Tag> listTag);
	
	/**
	 * Return a tag from a list of tag.
	 * 
	 * @param key Key of the tag to search
	 * @param listTag List of tag in which to search
	 * @return Tag found
	 */
	public Tag getTag(String key, List<Tag> listTag);
	
}
