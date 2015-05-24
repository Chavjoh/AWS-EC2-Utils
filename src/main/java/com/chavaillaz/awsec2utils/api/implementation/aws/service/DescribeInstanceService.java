package com.chavaillaz.awsec2utils.api.implementation.aws.service;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.Tag;
import com.chavaillaz.awsec2utils.api.implementation.common.AuthService_A;
import com.chavaillaz.awsec2utils.api.specification.aws.service.DescribeInstanceService_I;

/**
 * Implementation of {@link DescribeInstanceService_I}
 * 
 * @author Johan Chavaillaz
 */
public class DescribeInstanceService extends AuthService_A implements DescribeInstanceService_I {

	public DescribeInstanceService(AmazonEC2Client aws) {
		super(aws);
	}

	public Instance getFirstInstance(Tag tag) {
		List<Instance> listInstance = getInstance(tag);
		
		for (Instance instance : listInstance) {
			return instance;
		}
		
		return null;
	}

	public List<Instance> getInstance(Tag tag) {
		DescribeInstancesRequest request = new DescribeInstancesRequest();

		List<String> tagValue = new ArrayList<String>();
		tagValue.add(tag.getValue());
		Filter filter = new Filter("tag:" + tag.getKey(), tagValue);

		DescribeInstancesResult result = aws.describeInstances(request.withFilters(filter));
		List<Instance> listInstance = new ArrayList<Instance>();
		
		for (Reservation reservation : result.getReservations()) {
			listInstance.addAll(reservation.getInstances());
		}
		
		return listInstance;
	}

	public Instance getInstance(String instanceId) {
		DescribeInstancesRequest request = new DescribeInstancesRequest();
		DescribeInstancesResult result = aws.describeInstances(request.withInstanceIds(instanceId));
		
		for (Reservation reservation : result.getReservations()) {
			for (Instance instance : reservation.getInstances()) {
				// In this context (searching by instanceId), it must be only one instance returned
				return instance;
			}
		}
		
		return null;
	}

}
