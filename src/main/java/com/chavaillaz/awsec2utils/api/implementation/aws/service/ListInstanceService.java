package com.chavaillaz.awsec2utils.api.implementation.aws.service;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.common.AuthService_A;
import com.chavaillaz.awsec2utils.api.specification.aws.service.ListInstanceService_I;

/**
 * Implementation of {@link ListInstanceService_I}
 * 
 * @author Johan Chavaillaz
 */
public class ListInstanceService extends AuthService_A implements ListInstanceService_I {

	public ListInstanceService(AmazonEC2Client aws) {
		super(aws);
	}

	public List<Instance> getListInstance() {
		DescribeInstancesRequest request = new DescribeInstancesRequest();

		List<String> tagValue = new ArrayList<String>();
		tagValue.add(Constants.TAG_GROUP_DEFAULT);
		Filter filter = new Filter("tag:" + Constants.TAG_GROUP_KEY, tagValue);

		DescribeInstancesResult result = aws.describeInstances(request.withFilters(filter));
		List<Reservation> reservations = result.getReservations();
		
		List<Instance> listInstance = new ArrayList<Instance>();

		for (Reservation reservation : reservations) {
			listInstance.addAll(reservation.getInstances());
		}
		
		return listInstance;
	}

}
