package com.chavaillaz.awsec2utils.use.command;

import java.util.List;

import com.chavaillaz.awsec2utils.use.exception.CommandParametersException;

/**
 * Abstract command. 
 * Describe constructor and methods of a command.
 * 
 * @author Johan Chavaillaz
 */
abstract public class Command_A {

	/**
	 * Key of the command.
	 * Used to find the corresponding class of a command.
	 */
	public static String KEY;
	
	/**
	 * Help of the command
	 */
	public static String HELP;
	
	/**
	 * Detailed help of the command
	 */
	public static String HELP_DETAIL;
	
	/**
	 * Create the commands with the given parameters.
	 * 
	 * @param parameters Parameters used to configure the command
	 * @throws CommandParametersException
	 */
	public Command_A(List<String> parameters) throws CommandParametersException {
		// Nothing
	}
	
	/**
	 * Run the job of the command.
	 * 
	 * @throws Exception
	 */
	public abstract void run() throws Exception;
	
	/**
	 * Cleaning after run command.
	 */
	public abstract void clean();

}
