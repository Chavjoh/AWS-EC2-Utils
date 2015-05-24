package com.chavaillaz.awsec2utils.use.command;

import java.util.List;

import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.arc.ArcService;
import com.chavaillaz.awsec2utils.use.exception.CommandParametersException;

/**
 * Delete a template file and the corresponding instance on AWS EC2.
 * 
 * @author Johan Chavaillaz
 */
public class CommandPurge extends CommandAuth {
	
	public static final String KEY = "purge";
	public static final String HELP = Constants.PREFIX + KEY;
	
	private String vmId;

	public CommandPurge(List<String> parameters) throws CommandParametersException {
		super(parameters);
		
		if (parameters.size() == 1) {
			vmId = parameters.get(0);
		} else {
			throw new CommandParametersException(this, "Invalid parameters.");
		}
	}

	@Override
	public void run() throws Exception {
		ArcService.getInstance().getTemplateService(aws).purge(vmId);
	}

}
