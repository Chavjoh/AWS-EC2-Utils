package com.chavaillaz.awsec2utils.api.implementation.aws.service;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;
import com.chavaillaz.awsec2utils.api.implementation.common.AuthService_A;
import com.chavaillaz.awsec2utils.api.specification.aws.service.TagInstanceService_I;

/**
 * Implementation of {@link TagInstanceService_I}
 * 
 * @author Johan Chavaillaz
 */
public class TagInstanceService extends AuthService_A implements TagInstanceService_I {

	public TagInstanceService(AmazonEC2Client aws) {
		super(aws);
	}

	public void tagInstance(List<Instance> instances, Tag tag) {
		List<Tag> listTag = new ArrayList<Tag>();
		listTag.add(tag);
		tagInstance(instances, listTag);
	}

	public void tagInstance(List<Instance> instances, List<Tag> listTag) {
		for (Instance instance : instances) {
			CreateTagsRequest createTagsRequest = new CreateTagsRequest();
			createTagsRequest.withResources(instance.getInstanceId()).withTags(listTag);
			aws.createTags(createTagsRequest);
		}
	}

	public Tag getTag(String key, List<Tag> listTag) {
		for (Tag tag:listTag) {
			if (key.equals(tag.getKey())) return tag;
		}
		return null;
	}

}
