package com.chavaillaz.awsec2utils.use.command;

import java.util.List;

import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.arc.ArcService;
import com.chavaillaz.awsec2utils.api.model.VmTemplate;
import com.chavaillaz.awsec2utils.use.exception.CommandParametersException;
import com.chavaillaz.awsec2utils.utils.VmState;

/**
 * Show all details of each VM Template.
 * 
 * @author Johan Chavaillaz
 */
public class CommandListDetails extends CommandAuth {

	public static final String KEY = "listdetails";
	public static final String HELP = Constants.PREFIX + KEY;
	public static String HELP_DETAIL = "Show list of all VM templates with all details for each.";

	public CommandListDetails(List<String> parameters) throws CommandParametersException {
		super(parameters);
	}

	@Override
	public void run() throws Exception {
		List<VmTemplate> listTemplate = ArcService.getInstance().getListService(aws).listWithState();
		
		for (VmTemplate template:listTemplate) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(template.getVmId());
			stringBuilder.append(" [State] ");
			
			if (template.hasState()) {
				stringBuilder.append(template.getState());
				
				if (template.hasDnsName()) {
					stringBuilder.append(" [DNS Name] ");
					stringBuilder.append(template.getDnsName());
				}
			} else {
				stringBuilder.append(VmState.TERMINATED);
			}
			
			stringBuilder.append(" [Image] ");
			stringBuilder.append(template.getImageId());
			stringBuilder.append(" [Type] ");
			stringBuilder.append(template.getInstanceType());
			stringBuilder.append(" [Security Group] ");
			stringBuilder.append(template.getSecurityGroup());
			System.out.println(stringBuilder.toString());
		}
	}
	
}
