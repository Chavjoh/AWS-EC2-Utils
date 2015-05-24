package com.chavaillaz.awsec2utils.api.implementation.aws.service;

import java.util.Arrays;
import java.util.List;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.CreateSecurityGroupRequest;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsRequest;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsResult;
import com.amazonaws.services.ec2.model.IpPermission;
import com.amazonaws.services.ec2.model.SecurityGroup;
import com.chavaillaz.awsec2utils.api.implementation.common.AuthService_A;
import com.chavaillaz.awsec2utils.api.specification.aws.service.SecurityGroupService_I;

/**
 * Implementation of {@link SecurityGroupService_I}
 * 
 * @author Johan Chavaillaz
 */
public class SecurityGroupService extends AuthService_A implements SecurityGroupService_I {

	public SecurityGroupService(AmazonEC2Client aws) {
		super(aws);
	}

	public List<SecurityGroup> listSecurityGroup() {
		DescribeSecurityGroupsRequest securityRequest = new DescribeSecurityGroupsRequest();
		DescribeSecurityGroupsResult securityDescription = aws.describeSecurityGroups(securityRequest);
		return securityDescription.getSecurityGroups();
	}

	public SecurityGroup getSecurityGroup(String securityGroupName) {
		DescribeSecurityGroupsRequest securityRequest = new DescribeSecurityGroupsRequest();
		securityRequest.setGroupNames(Arrays.asList(securityGroupName));
		DescribeSecurityGroupsResult securityDescription = aws.describeSecurityGroups(securityRequest);
		return securityDescription.getSecurityGroups().get(0);
	}

	public void createSecurityGroupWithSshPermission(String securityGroupName, String securityGroupDescription) {
		CreateSecurityGroupRequest createSecurityGroupRequest = new CreateSecurityGroupRequest();
		createSecurityGroupRequest.withGroupName(securityGroupName).withDescription(securityGroupDescription);
		aws.createSecurityGroup(createSecurityGroupRequest);
		
		IpPermission ipPermission = new IpPermission();
		ipPermission.withIpRanges("0.0.0.0/0").withIpProtocol("tcp").withFromPort(22).withToPort(22);

		AuthorizeSecurityGroupIngressRequest authorizeSecurityGroupIngressRequest = new AuthorizeSecurityGroupIngressRequest();
		authorizeSecurityGroupIngressRequest.withGroupName(securityGroupName).withIpPermissions(ipPermission);
		aws.authorizeSecurityGroupIngress(authorizeSecurityGroupIngressRequest);
	}

}
