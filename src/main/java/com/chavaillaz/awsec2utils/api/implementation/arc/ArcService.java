package com.chavaillaz.awsec2utils.api.implementation.arc;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.chavaillaz.awsec2utils.api.implementation.arc.service.CloneService;
import com.chavaillaz.awsec2utils.api.implementation.arc.service.ConfigService;
import com.chavaillaz.awsec2utils.api.implementation.arc.service.RebootService;
import com.chavaillaz.awsec2utils.api.implementation.arc.service.SshService;
import com.chavaillaz.awsec2utils.api.implementation.arc.service.TemplateService;
import com.chavaillaz.awsec2utils.api.implementation.arc.service.ListService;
import com.chavaillaz.awsec2utils.api.implementation.arc.service.RunService;
import com.chavaillaz.awsec2utils.api.implementation.arc.service.SendService;
import com.chavaillaz.awsec2utils.api.implementation.arc.service.StartService;
import com.chavaillaz.awsec2utils.api.implementation.arc.service.StopService;
import com.chavaillaz.awsec2utils.api.implementation.arc.service.TerminateService;
import com.chavaillaz.awsec2utils.api.specification.arc.ArcService_I;
import com.chavaillaz.awsec2utils.api.specification.arc.service.CloneService_I;
import com.chavaillaz.awsec2utils.api.specification.arc.service.ConfigService_I;
import com.chavaillaz.awsec2utils.api.specification.arc.service.RebootService_I;
import com.chavaillaz.awsec2utils.api.specification.arc.service.SshService_I;
import com.chavaillaz.awsec2utils.api.specification.arc.service.TemplateService_I;
import com.chavaillaz.awsec2utils.api.specification.arc.service.ListService_I;
import com.chavaillaz.awsec2utils.api.specification.arc.service.RunService_I;
import com.chavaillaz.awsec2utils.api.specification.arc.service.SendService_I;
import com.chavaillaz.awsec2utils.api.specification.arc.service.StartService_I;
import com.chavaillaz.awsec2utils.api.specification.arc.service.StopService_I;
import com.chavaillaz.awsec2utils.api.specification.arc.service.TerminateService_I;

/**
 * Implementation of {@link ArcService_I}
 * 
 * @author Johan Chavaillaz
 */
public class ArcService implements ArcService_I {

	public static ArcService instance;
	
	public static ArcService_I getInstance() {
		if (instance == null) {
			instance = new ArcService();
		}
		return instance;
	}

	public CloneService_I getCloneService() {
		return new CloneService();
	}

	public ConfigService_I getConfigService(AmazonEC2Client aws) {
		return new ConfigService(aws);
	}

	public TemplateService_I getTemplateService(AmazonEC2Client aws) {
		return new TemplateService(aws);
	}
	
	public ListService_I getListService(AmazonEC2Client aws) {
		return new ListService(aws);
	}

	public RunService_I getRunService(AmazonEC2Client aws) {
		return new RunService(aws);
	}

	public SendService_I getSendService(AmazonEC2Client aws) {
		return new SendService(aws);
	}

	public StartService_I getStartService(AmazonEC2Client aws) {
		return new StartService(aws);
	}

	public StopService_I getStopService(AmazonEC2Client aws) {
		return new StopService(aws);
	}
	
	public RebootService_I getRebootService(AmazonEC2Client aws) {
		return new RebootService(aws);
	}

	public TerminateService_I getTerminateService(AmazonEC2Client aws) {
		return new TerminateService(aws);
	}

	public SshService_I getSshService(AmazonEC2Client aws) {
		return new SshService(aws);
	}

}
