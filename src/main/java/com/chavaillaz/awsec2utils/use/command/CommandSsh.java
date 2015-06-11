package com.chavaillaz.awsec2utils.use.command;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;
import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.arc.ArcService;
import com.chavaillaz.awsec2utils.api.implementation.aws.AwsService;
import com.chavaillaz.awsec2utils.api.model.Configuration;
import com.chavaillaz.awsec2utils.use.exception.CommandParametersException;
import com.chavaillaz.awsec2utils.utils.StringShop;

/**
 * Run user specific commands on an AWS EC2 instance corresponding to a template.
 * 
 * @author Johan Chavaillaz
 */
public class CommandSsh extends CommandAuth {
	
	public static final String KEY = "ssh";
	public static final String HELP = Constants.PREFIX + KEY + " vmId [username]";
	public static String HELP_DETAIL = "Launch a SSH connexion with the selected instance.";
	
	private static final String SSH_COMMAND = "ssh -i ";
	
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

		if (SystemUtils.IS_OS_LINUX) {
			launchLinuxSsh();
		} else if (SystemUtils.IS_OS_MAC) {
			launchMacSsh();
		} else {
			launchSimulator();
		}
		
	}
	
	public void launchSimulator() throws Exception {
		final Scanner scanner = new Scanner(System.in);
		SSHClient ssh = null;

		try {
			ssh = ArcService.getInstance().getSshService(aws).getSshClient(vmId, username);
			
			// SSH Simulator
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

	public void launchMacSsh() throws IOException {
		String scriptPath = Constants.VM_FOLDER + vmId + ".sh";
		//String fullCommand = "osascript -e 'tell application \"Terminal\" to do script \"" + getSshCommand() + "\"'";
		String fullCommand = getSshCommand();
		FileUtils.writeStringToFile(new File(scriptPath), fullCommand);
		Runtime rt = Runtime.getRuntime();
		rt.exec("chmod 700 " + Configuration.getConfiguration().getKeyPairPath());
		rt.exec("chmod a+x " + scriptPath);
		rt.exec("open -a Terminal " + scriptPath);
	}
	
	public void launchLinuxSsh() throws IOException {
		Runtime rt = Runtime.getRuntime();
		rt.exec("xterm -hold -e " + getSshCommand());
	}
	
	public String getSshCommand() throws IOException {
		Instance instance = AwsService.getInstance()
				.getDescribeInstanceService(aws)
				.getFirstSignificantInstance(new Tag(Constants.TAG_KEY, vmId));
		
		File currentDirectory = new File(new File(".").getAbsolutePath());
		StringBuilder command = new StringBuilder();
		command.append(SSH_COMMAND);
		command.append(StringShop.APOSTROPHE);
		command.append(currentDirectory.getCanonicalPath());
		command.append(StringShop.SLASH);
		command.append(Configuration.getConfiguration().getKeyPairPath());
		command.append(StringShop.APOSTROPHE);
		command.append(StringShop.SPACE);
		command.append(username);
		command.append(StringShop.AT);
		command.append(instance.getPublicDnsName());
		
		return command.toString();
	}

}
