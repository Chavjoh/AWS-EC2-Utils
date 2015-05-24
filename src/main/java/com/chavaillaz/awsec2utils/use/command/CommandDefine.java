package com.chavaillaz.awsec2utils.use.command;

import java.util.List;

import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.arc.ArcService;
import com.chavaillaz.awsec2utils.use.exception.CommandParametersException;

/**
 * Define a template file to manage a virtual machine.
 * 
 * @author Johan Chavaillaz
 */
public class CommandDefine extends CommandAuth {
	
	public static final String KEY = "define";
	public static String HELP = Constants.PREFIX + KEY + " vmId imageId instanceType [securityGroup]";
	
	private String vmId;
	private String imageId;
	private String instanceType;
	private String securityGroupName;
	
	public CommandDefine(List<String> parameters) throws CommandParametersException {
		super(parameters);
		
		if (parameters.size() == 3 || parameters.size() == 4) {
			vmId = parameters.get(0);
			imageId = parameters.get(1);
			instanceType = parameters.get(2);
			securityGroupName = (parameters.size() == 4) ? parameters.get(3) : Constants.AWS_SECURITY_GROUP;
		} else {
			throw new CommandParametersException(this, "Invalid parameters.");
		}
	}

	@Override
	public void run() throws Exception {
		ArcService.getInstance().getTemplateService(aws).define(vmId, imageId, instanceType, securityGroupName);
	}
	
}
