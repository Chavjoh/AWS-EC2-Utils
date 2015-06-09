package com.chavaillaz.awsec2utils.use.command;

import java.util.List;

import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.arc.ArcService;
import com.chavaillaz.awsec2utils.api.model.VmTemplate;
import com.chavaillaz.awsec2utils.use.exception.CommandParametersException;
import com.chavaillaz.awsec2utils.utils.StringShop;
import com.chavaillaz.awsec2utils.utils.VmState;

/**
 * Show all templates with state on AWS EC2.
 * 
 * @author Johan Chavaillaz
 */
public class CommandListState extends CommandAuth {

	public static final String KEY = "liststate";
	public static final String HELP = Constants.PREFIX + KEY;
	public static String HELP_DETAIL = "Show list of all VM templates with state for each.";
	
	public CommandListState(List<String> parameters) throws CommandParametersException {
		super(parameters);
	}

	@Override
	public void run() throws Exception {
		List<VmTemplate> listTemplate = ArcService.getInstance().getListService(aws).listWithState();
		
		for (VmTemplate template:listTemplate) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(template.getVmId());
			stringBuilder.append(StringShop.SPACE);
			
			if (template.hasState()) {
				stringBuilder.append(template.getState());
			} else {
				stringBuilder.append(VmState.TERMINATED);
			}
			
			System.out.println(stringBuilder.toString());
		}
	}

}
