package com.chavaillaz.awsec2utils.use.command;

import java.util.List;

import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.arc.ArcService;
import com.chavaillaz.awsec2utils.use.exception.CommandParametersException;

/**
 * Reboot an instance corresponding to a template.
 * 
 * @author Johan Chavaillaz
 */
public class CommandReboot extends CommandAuth {

	public static final String KEY = "reboot";
	public static final String HELP = Constants.PREFIX + KEY + " vmId";
	public static String HELP_DETAIL = "Reboot a VM.";
	
	private String vmId;
	
	public CommandReboot(List<String> parameters) throws CommandParametersException {
		super(parameters);
		
		if (parameters.size() == 1) {
			vmId = parameters.get(0);
		} else {
			throw new CommandParametersException(this, "Invalid parameters.");
		}
	}
	
	@Override
	public void run() throws Exception {
		ArcService.getInstance().getRebootService(aws).reboot(vmId);
	}
	
}
