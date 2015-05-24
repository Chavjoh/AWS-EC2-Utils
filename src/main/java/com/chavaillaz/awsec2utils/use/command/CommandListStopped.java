package com.chavaillaz.awsec2utils.use.command;

import java.util.List;

import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.arc.ArcService;
import com.chavaillaz.awsec2utils.api.model.VmTemplate;
import com.chavaillaz.awsec2utils.use.exception.CommandParametersException;
import com.chavaillaz.awsec2utils.utils.VmState;

/**
 * Show all stopped templates on AWS EC2.
 * 
 * @author Johan Chavaillaz
 */
public class CommandListStopped extends CommandAuth {
	
	public static final String KEY = "liststopped";
	public static final String HELP = Constants.PREFIX + KEY;

	public CommandListStopped(List<String> parameters) throws CommandParametersException {
		super(parameters);
	}

	@Override
	public void run() throws Exception {
		List<VmTemplate> listTemplate = ArcService.getInstance().getListService(aws).listState(VmState.STOPPED);
		
		for (VmTemplate template:listTemplate) {
			System.out.println(template.getVmId());
		}
	}

}
