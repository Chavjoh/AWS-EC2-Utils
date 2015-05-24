package com.chavaillaz.awsec2utils.use.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Factory to get and store all commands.
 * 
 * @author Johan Chavaillaz
 */
public class CommandFactory {
	
	private Map<String, Class<? extends Command_A>> listCommand;

	private static CommandFactory instance;
	
	private CommandFactory() {
		listCommand = new HashMap<String, Class<? extends Command_A>>();
		registerAllCommands();
	}
	
	public static CommandFactory getInstance() {
		if (instance == null) {
			instance = new CommandFactory();
		}
		return instance;
	}
	
	public Map<String, Class<? extends Command_A>> getListCommand() {
		return listCommand;
	}
	
	public void registerAllCommands() {
		listCommand.put(CommandConfig.KEY, CommandConfig.class);
		listCommand.put(CommandDefine.KEY, CommandDefine.class);
		listCommand.put(CommandClone.KEY, CommandClone.class);
		listCommand.put(CommandStart.KEY, CommandStart.class);
		listCommand.put(CommandStop.KEY, CommandStop.class);
		listCommand.put(CommandReboot.KEY, CommandReboot.class);
		listCommand.put(CommandTerminate.KEY, CommandTerminate.class);
		listCommand.put(CommandTerminateAll.KEY, CommandTerminateAll.class);
		listCommand.put(CommandList.KEY, CommandList.class);
		listCommand.put(CommandListState.KEY, CommandListState.class);
		listCommand.put(CommandListDetails.KEY, CommandListDetails.class);
		listCommand.put(CommandListStopped.KEY, CommandListStopped.class);
		listCommand.put(CommandListRunning.KEY, CommandListRunning.class);
		listCommand.put(CommandPurge.KEY, CommandPurge.class);
		listCommand.put(CommandSend.KEY, CommandSend.class);
		listCommand.put(CommandSsh.KEY, CommandSsh.class);
		listCommand.put(CommandRun.KEY, CommandRun.class);
		listCommand.put(CommandHelp.KEY, CommandHelp.class);
	}
	
	public Command_A getCommand(String[] parameters) throws Exception {
		ArrayList<String> listParameters = new ArrayList<String>();
		listParameters.addAll(Arrays.asList(parameters));
		String commandKey = listParameters.remove(0);
		Class<? extends Command_A> className = listCommand.get(commandKey);
		return (className != null) ? className.getConstructor(List.class).newInstance(listParameters) : null;
	}
	
}
