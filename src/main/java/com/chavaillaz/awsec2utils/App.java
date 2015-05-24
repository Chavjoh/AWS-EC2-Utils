package com.chavaillaz.awsec2utils;

import com.chavaillaz.awsec2utils.use.command.CommandFactory;
import com.chavaillaz.awsec2utils.use.command.Command_A;
import com.chavaillaz.awsec2utils.use.exception.CommandParametersException;
import com.chavaillaz.awsec2utils.utils.ExceptionUtils;

/**
 * Main class to use the command line tool
 * 
 * @author Johan Chavaillaz
 */
public class App {
	
	public static void main(String[] args) throws Exception {
		
		if (args.length == 0) {
			System.out.println("Command name is missing.");
			System.exit(0);
		}

		CommandFactory factory = CommandFactory.getInstance();
		Command_A command = null;
		
		try {
			command = factory.getCommand(args);
						
			if (command != null) {
				command.run();	
			} else {
				System.out.println("Command not found.");
			}
			
		} catch (Exception e) {
			if (e.getCause() != null) {
				if (e.getCause() instanceof CommandParametersException) {
					System.out.println(e.getCause());
				} else {
					System.out.println(ExceptionUtils.getStackTrace(e.getCause()));
				}
			} else {
				System.out.println(ExceptionUtils.getStackTrace(e));
			}
		} finally {
			if (command != null) {
				command.clean();
			}
		}
	}
	
}
