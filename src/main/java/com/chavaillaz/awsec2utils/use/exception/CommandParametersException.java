package com.chavaillaz.awsec2utils.use.exception;

import com.chavaillaz.awsec2utils.use.command.Command_A;

/**
 * Exception representing bad parameters (format or number) for the command.
 * 
 * @author Johan Chavaillaz
 */
public class CommandParametersException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private Command_A command;
	private String message;
	
	public CommandParametersException(Command_A command, String message) {
		this.command = command;
		this.message = message;
		command.clean();
	}

	@Override
	public String toString() {
		return "[" + command.getClass().getSimpleName() + "] " + message;
	}

}
