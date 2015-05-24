package com.chavaillaz.awsec2utils.use.command;

import java.util.List;

import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.arc.ArcService;
import com.chavaillaz.awsec2utils.api.model.VmTemplate;
import com.chavaillaz.awsec2utils.use.exception.CommandParametersException;

/**
 * Show all templates list.
 * 
 * @author Johan Chavaillaz
 */
public class CommandList extends CommandAuth {
	
	public static final String KEY = "list";
	public static String HELP = Constants.PREFIX + KEY;

	public CommandList(List<String> parameters) throws CommandParametersException {
		super(parameters);
	}

	@Override
	public void run() throws Exception {
		List<VmTemplate> listTemplate = ArcService.getInstance().getListService(aws).list();
		
		for (VmTemplate template:listTemplate) {
			System.out.println(template.getVmId());
		}
	}

}
