package com.chavaillaz.awsec2utils.api.implementation.aws.service;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesResult;
import com.chavaillaz.awsec2utils.api.implementation.common.AuthService_A;
import com.chavaillaz.awsec2utils.api.specification.aws.service.TerminateInstanceService_I;

/**
 * Implementation of {@link TerminateInstanceService_I}
 * 
 * @author Johan Chavaillaz
 */
public class TerminateInstanceService extends AuthService_A implements TerminateInstanceService_I {

	public TerminateInstanceService(AmazonEC2Client aws) {
		super(aws);
	}

	public TerminateInstancesResult terminateInstance(Instance instance) {
		return terminateInstance(instance.getInstanceId());
	}

	public TerminateInstancesResult terminateInstance(String instanceId) {
		TerminateInstancesRequest terminateInstancesRequest = new TerminateInstancesRequest().withInstanceIds(instanceId);
		return aws.terminateInstances(terminateInstancesRequest);
	}

}
