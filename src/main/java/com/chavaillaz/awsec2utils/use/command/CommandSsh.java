package com.chavaillaz.awsec2utils.use.command;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;
import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.arc.ArcService;
import com.chavaillaz.awsec2utils.use.exception.CommandParametersException;

/**
 * Run user specific commands on an AWS EC2 instance corresponding to a template.
 * 
 * @author Johan Chavaillaz
 */
public class CommandSsh extends CommandAuth {
	
	public static final String KEY = "ssh";
	public static final String HELP = Constants.PREFIX + KEY + " vmId [username]";
	
	private String vmId;
	private String username;

	public CommandSsh(List<String> parameters) throws CommandParametersException {
		super(parameters);
		
		if (parameters.size() == 1 || parameters.size() == 2) {
			vmId = parameters.get(0);
			username = (parameters.size() == 2) ? parameters.get(1) : "root";
		} else {
			throw new CommandParametersException(this, "Invalid parameters.");
		}
	}

	@Override
	public void run() throws Exception {
		final Scanner scanner = new Scanner(System.in);
		SSHClient ssh = null;

		try {
			ssh = ArcService.getInstance().getSshService(aws).getSshClient(vmId, username);
			
			System.out.println("Write your command and press enter to execute it on the instance.");
			System.out.println("Be careful : Navigation is not available.");
			System.out.println("             Please use absolute path to execute your command.");
			System.out.println("Enter 'exit' to quit.");

			while (true) {
				String input = scanner.nextLine();
				
				if (input.equals("exit"))
					break;
				
				final Session session = ssh.startSession();
	
				try {
					final Command cmd = session.exec(input);
					System.out.println(IOUtils.readFully(cmd.getInputStream()).toString());
					cmd.join(30, TimeUnit.SECONDS);
				} finally {
					session.close();
				}
			}
		} finally {
			scanner.close();
			
			if (ssh != null) {
				ssh.disconnect();
				ssh.close();
			}
		}
	}

}
