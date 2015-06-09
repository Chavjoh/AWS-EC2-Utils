package com.chavaillaz.awsec2utils.use.command;

import java.util.List;

import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.arc.ArcService;
import com.chavaillaz.awsec2utils.use.exception.CommandParametersException;

/**
 * Run a specific file/folder on an AWS EC2 instance corresponding to a template.
 * Transfer it to the instance from the current disk.
 * If it's a folder, run each shell files inside.
 * 
 * @author Johan Chavaillaz
 */
public class CommandRun extends CommandAuth {
	
	public static final String KEY = "run";
	public static final String HELP = Constants.PREFIX + KEY + " vmId source destination [username]";
	public static String HELP_DETAIL = "Transfer and launch a program or all program in a directory on the selected instance. To be executed files must have SH or RUN extension.";
	
	private String vmId;
	private String source;
	private String destination;
	private String username;

	public CommandRun(List<String> parameters) throws CommandParametersException {
		super(parameters);
		
		if (parameters.size() == 3 || parameters.size() == 4) {
			vmId = parameters.get(0);
			source = parameters.get(1);
			destination = parameters.get(2);
			username = (parameters.size() == 4) ? parameters.get(3) : "root";
		} else {
			throw new CommandParametersException(this, "Invalid parameters.");
		}
	}

	@Override
	public void run() throws Exception {
		ArcService.getInstance().getRunService(aws).run(vmId, source, destination, username);
	}

}
