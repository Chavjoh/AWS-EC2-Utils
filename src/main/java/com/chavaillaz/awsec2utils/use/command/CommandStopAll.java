package com.chavaillaz.awsec2utils.use.command;

import java.util.List;

import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.arc.ArcService;
import com.chavaillaz.awsec2utils.use.exception.CommandParametersException;

/**
 * Stop all template and their corresponding AWS EC2 instance.
 * 
 * @author Johan Chavaillaz
 */
public class CommandStopAll extends CommandAuth {

	public static final String KEY = "stop";
	public static final String HELP = Constants.PREFIX + KEY;
	public static String HELP_DETAIL = "Stop all VM.";

	public CommandStopAll(List<String> parameters) throws CommandParametersException {
		super(parameters);
	}

	@Override
	public void run() throws Exception {
		ArcService.getInstance().getStopService(aws).stopAll();
	}

}
