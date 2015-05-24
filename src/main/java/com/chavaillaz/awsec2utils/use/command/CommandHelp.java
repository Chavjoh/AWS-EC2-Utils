package com.chavaillaz.awsec2utils.use.command;

import java.util.List;
import java.util.Map;

import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.use.exception.CommandParametersException;

/**
 * Show help of each command.
 * 
 * @author Johan Chavaillaz
 */
public class CommandHelp extends Command_A {
	
	public static final String KEY = "help";
	public static final String HELP = Constants.PREFIX + "help";

	public CommandHelp(List<String> parameters) throws CommandParametersException {
		super(parameters);
	}

	@Override
	public void run() throws Exception {
		Map<String, Class<? extends Command_A>> listCommand = CommandFactory.getInstance().getListCommand();
		
		for (Map.Entry<String, Class<? extends Command_A>> entry:listCommand.entrySet()) {
			Object help = entry.getValue().getField("HELP").get(null);
			
			if (help != null) {
				System.out.println(help);
			}
		}
	}

	@Override
	public void clean() {
		// Nothing
	}

}
