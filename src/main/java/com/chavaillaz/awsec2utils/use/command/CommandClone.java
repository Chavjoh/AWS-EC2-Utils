package com.chavaillaz.awsec2utils.use.command;

import java.util.List;

import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.arc.ArcService;
import com.chavaillaz.awsec2utils.use.exception.CommandParametersException;

/**
 * Clone a VM configuration file to a new one.
 * 
 * @author Johan Chavaillaz
 */
public class CommandClone extends Command_A {
	
	public static final String KEY = "clone";
	public static String HELP = Constants.PREFIX + KEY + " newVmId vmId";
	
	private String newVmId;
	private String vmId;

	public CommandClone(List<String> parameters) throws CommandParametersException {
		super(parameters);
		
		if (parameters.size() == 2) {
			newVmId = parameters.get(0);
			vmId = parameters.get(1);
		} else {
			throw new CommandParametersException(this, "Invalid parameters.");
		}
	}

	@Override
	public void run() throws Exception {
		ArcService.getInstance().getCloneService().clone(newVmId, vmId);
	}

	@Override
	public void clean() {
		// Nothing
	}

}
