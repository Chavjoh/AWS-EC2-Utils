package com.chavaillaz.awsec2utils.api.implementation.aws.service;

import java.util.List;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesResult;
import com.chavaillaz.awsec2utils.api.implementation.common.AuthService_A;
import com.chavaillaz.awsec2utils.api.specification.aws.service.StopInstanceService_I;

/**
 * Implementation of {@link StopInstanceService_I}
 * 
 * @author Johan Chavaillaz
 */
public class StopInstanceService extends AuthService_A implements StopInstanceService_I {

	public StopInstanceService(AmazonEC2Client aws) {
		super(aws);
	}

	public StopInstancesResult stopInstance(Instance instance) {
		return stopInstance(instance.getInstanceId());
	}

	public StopInstancesResult stopInstance(String instanceId) {
		StopInstancesRequest stopInstancesRequest = new StopInstancesRequest().withInstanceIds(instanceId);
		return aws.stopInstances(stopInstancesRequest);
	}

	public StopInstancesResult stopInstance(List<Instance> listInstance) {
		StopInstancesRequest stopInstancesRequest = new StopInstancesRequest();
		
		for (Instance instance:listInstance) {
			stopInstancesRequest.withInstanceIds(instance.getInstanceId());
		}
		
		return aws.stopInstances(stopInstancesRequest);
	}

}
