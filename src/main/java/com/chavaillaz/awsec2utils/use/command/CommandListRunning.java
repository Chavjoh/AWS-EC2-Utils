package com.chavaillaz.awsec2utils.use.command;

import java.util.List;

import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.arc.ArcService;
import com.chavaillaz.awsec2utils.api.model.VmTemplate;
import com.chavaillaz.awsec2utils.use.exception.CommandParametersException;
import com.chavaillaz.awsec2utils.utils.VmState;

/**
 * Show all running templates on AWS EC2.
 * 
 * @author Johan Chavaillaz
 */
public class CommandListRunning extends CommandAuth {

	public static final String KEY = "listrunning";
	public static final String HELP = Constants.PREFIX + KEY;
	public static String HELP_DETAIL = "Show list of all VM templates with running state.";

	public CommandListRunning(List<String> parameters) throws CommandParametersException {
		super(parameters);
	}

	@Override
	public void run() throws Exception {
		List<VmTemplate> listTemplate = ArcService.getInstance().getListService(aws).listState(VmState.RUNNING);
		
		for (VmTemplate template:listTemplate) {
			System.out.println(template.getVmId());
		}
	}

}
