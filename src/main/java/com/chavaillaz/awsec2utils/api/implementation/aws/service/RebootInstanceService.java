package com.chavaillaz.awsec2utils.api.implementation.aws.service;

import com.amazonaws.services.ec2.AmazonEC2Client;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.RebootInstancesRequest;
import com.chavaillaz.awsec2utils.api.implementation.common.AuthService_A;
import com.chavaillaz.awsec2utils.api.specification.aws.service.RebootInstanceService_I;

/**
 * Implementation of {@link RebootInstanceService_I}
 * 
 * @author Johan Chavaillaz
 */
public class RebootInstanceService extends AuthService_A implements RebootInstanceService_I {

	public RebootInstanceService(AmazonEC2Client aws) {
		super(aws);
	}

	public void reboot(Instance instance) {
		reboot(instance.getInstanceId());
	}

	public void reboot(String instanceId) {
		RebootInstancesRequest rebootInstancesRequest = new RebootInstancesRequest().withInstanceIds(instanceId);
		aws.rebootInstances(rebootInstancesRequest);
	}

}
