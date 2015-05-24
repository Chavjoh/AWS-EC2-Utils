package com.chavaillaz.awsec2utils.api.specification.arc;

import com.amazonaws.services.ec2.AmazonEC2Client;
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
 * Services used in the business part of AWS-EC2-Utils.
 * 
 * @author Johan Chavaillaz
 */
public interface ArcService_I {
	
	/**
	 * @return A new clone service
	 */
	public CloneService_I getCloneService();
	
	/**
	 * @return A new config service
	 */
	public ConfigService_I getConfigService(AmazonEC2Client aws);
	
	/**
	 * @return A new template service
	 */
	public TemplateService_I getTemplateService(AmazonEC2Client aws);
	
	/**
	 * @return A new list service
	 */
	public ListService_I getListService(AmazonEC2Client aws);
	
	/**
	 * @return A new SSH service
	 */
	public SshService_I getSshService(AmazonEC2Client aws);
	
	/**
	 * @return A new run service
	 */
	public RunService_I getRunService(AmazonEC2Client aws);
	
	/**
	 * @return A new send service
	 */
	public SendService_I getSendService(AmazonEC2Client aws);
	
	/**
	 * @return A new start service
	 */
	public StartService_I getStartService(AmazonEC2Client aws);
	
	/**
	 * @return A new stop service
	 */
	public StopService_I getStopService(AmazonEC2Client aws);
	
	/**
	 * @return A new reboot service
	 */
	public RebootService_I getRebootService(AmazonEC2Client aws);
	
	/**
	 * @return A new terminate service
	 */
	public TerminateService_I getTerminateService(AmazonEC2Client aws);

}
