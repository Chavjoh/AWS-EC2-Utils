package com.chavaillaz.awsec2utils.use.command;

import java.util.List;

import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.arc.ArcService;
import com.chavaillaz.awsec2utils.use.exception.CommandParametersException;

/**
 * Start a template corresponding to an AWS EC2 instance.
 * 
 * @author Johan Chavaillaz
 */
public class CommandStart extends CommandAuth {
	
	public static final String KEY = "start";
	public static final String HELP = Constants.PREFIX + KEY + " vmId";
	public static String HELP_DETAIL = "Start a VM.";
	
	private String vmId;
	
	public CommandStart(List<String> parameters) throws CommandParametersException {
		super(parameters);
		
		if (parameters.size() == 1) {
			vmId = parameters.get(0);
		} else {
			throw new CommandParametersException(this, "Invalid parameters.");
		}
	}
	
	@Override
	public void run() throws Exception {
		ArcService.getInstance().getStartService(aws).start(vmId);
	}
	
}
