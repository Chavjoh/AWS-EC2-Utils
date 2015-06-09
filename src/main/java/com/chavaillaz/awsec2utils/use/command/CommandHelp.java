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
	public static final String HELP = Constants.PREFIX + "help [command]";
	public static String HELP_DETAIL = "Show help of the tool. Specify the command name to show detailed help.";
	
	private static final String SPACING = "  ";
	
	private String command = null;

	public CommandHelp(List<String> parameters) throws CommandParametersException {
		super(parameters);
		
		if (parameters.size() == 1) {
			command = parameters.get(0);
		}
	}

	@Override
	public void run() throws Exception {
		
		if (command != null) {
			
			Class<? extends Command_A> commandClass = CommandFactory.getInstance().getListCommand().get(command);
			
			if (commandClass == null) {
				System.out.println("Command not found.");
			} else {
				System.out.println();
				System.out.println(SPACING + commandClass.getField("HELP").get(null));
				System.out.println(SPACING + commandClass.getField("HELP_DETAIL").get(null));
				System.out.println();
			}
			
		} else {
			Map<String, Class<? extends Command_A>> listCommand = CommandFactory.getInstance().getListCommand();
			
			System.out.println("To show detailed help of a specific command, use " + HELP);
			System.out.println();
			System.out.println("Command list :");
			System.out.println("==============");
			
			for (Map.Entry<String, Class<? extends Command_A>> entry:listCommand.entrySet()) {
				Object help = entry.getValue().getField("HELP").get(null);
				
				if (help != null) {
					System.out.println(SPACING + help);
				}
			}
			
			System.out.println();
			System.out.println("VM state :");
			System.out.println("==========");
			System.out.println("VM Template -(START)-> Running -----(STOP)-----> Stopped ---(KILL)---> VM Template\r\n");
			System.out.println("Free ----------------> Paying (VM + Storage) --> Paying (Storage) ---> Free\r\n");
			System.out.println("                       Expensive                 Cheaper");
			System.out.println();
		}
	}

	@Override
	public void clean() {
		// Nothing
	}

}
