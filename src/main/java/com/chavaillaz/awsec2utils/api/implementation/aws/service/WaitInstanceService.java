package com.chavaillaz.awsec2utils.api.implementation.aws.service;

import java.util.List;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.chavaillaz.awsec2utils.api.implementation.common.AuthService_A;
import com.chavaillaz.awsec2utils.api.specification.aws.service.WaitInstanceService_I;
import com.chavaillaz.awsec2utils.utils.VmState;

/**
 * Implementation of {@link WaitInstanceService_I}
 * 
 * @author Johan Chavaillaz
 */
public class WaitInstanceService extends AuthService_A implements WaitInstanceService_I {

	public WaitInstanceService(AmazonEC2Client aws) {
		super(aws);
	}

	public void waitInstance(List<Instance> listInstance, String finalState) throws InterruptedException {
		for (Instance instance : listInstance) {
			waitInstance(instance.getInstanceId(), finalState);
		}
	}

	public void waitInstance(List<Instance> listInstance) throws InterruptedException {
		for (Instance instance : listInstance) {
			waitInstance(instance.getInstanceId(), VmState.RUNNING);
		}
	}

	public void waitInstance(Instance instance, String finalState) throws InterruptedException {
		waitInstance(instance.getInstanceId(), finalState);
	}

	public void waitInstance(Instance instance) throws InterruptedException {
		waitInstance(instance.getInstanceId(), VmState.RUNNING);
	}

	public void waitInstance(String instanceId) throws InterruptedException {
		waitInstance(instanceId, VmState.RUNNING);
	}

	public void waitInstance(String instanceId, String finalState) throws InterruptedException {
		/*
		 * Be careful : DescribeInstanceStatus returns information only for instances in the running state !
		 * We use here DescribeInstances to get all instances regardless of their state. 
		 */
		DescribeInstancesRequest describeInstanceRequest = new DescribeInstancesRequest().withInstanceIds(instanceId);
		DescribeInstancesResult describeInstanceResult = aws.describeInstances(describeInstanceRequest);
		Instance instance = describeInstanceResult.getReservations().get(0).getInstances().get(0);
		
		while (!instance.getState().getName().equals(finalState)) { 
		    describeInstanceResult = aws.describeInstances(describeInstanceRequest);
    		instance = describeInstanceResult.getReservations().get(0).getInstances().get(0);
		    
		    Thread.sleep(1000);
		}
	}

}
