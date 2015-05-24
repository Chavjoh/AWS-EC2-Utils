package com.chavaillaz.awsec2utils.api.implementation.aws.service;

import java.io.IOException;
import java.util.List;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesResult;
import com.chavaillaz.awsec2utils.api.implementation.common.AuthService_A;
import com.chavaillaz.awsec2utils.api.specification.aws.service.StartInstanceService_I;

/**
 * Implementation of {@link StartInstanceService_I}
 * 
 * @author Johan Chavaillaz
 */
public class StartInstanceService extends AuthService_A implements StartInstanceService_I {

	public StartInstanceService(AmazonEC2Client aws) {
		super(aws);
	}

	public StartInstancesResult startInstance(Instance instance) {
		return startInstance(instance.getInstanceId());
	}

	public StartInstancesResult startInstance(String instanceId) {
		StartInstancesRequest startInstancesRequest = new StartInstancesRequest().withInstanceIds(instanceId);
		return aws.startInstances(startInstancesRequest);
	}

	public StartInstancesResult startInstance(List<Instance> listInstance) {
		StartInstancesRequest startInstancesRequest = new StartInstancesRequest();
		
		for (Instance instance:listInstance) {
			startInstancesRequest.withInstanceIds(instance.getInstanceId());
		}
		
		return aws.startInstances(startInstancesRequest);
	}

	public List<Instance> runInstance(String imageId, String instanceType, String keyName, String securityGroup) throws IOException {
		RunInstancesRequest runInstancesRequest = new RunInstancesRequest();

		runInstancesRequest
				.withImageId(imageId)
				.withInstanceType(instanceType)
				.withMinCount(1)
				.withMaxCount(1)
				.withKeyName(keyName)
				.withSecurityGroups(securityGroup);
		
		return aws.runInstances(runInstancesRequest).getReservation().getInstances();
	}
}
