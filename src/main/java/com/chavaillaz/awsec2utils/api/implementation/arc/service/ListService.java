package com.chavaillaz.awsec2utils.api.implementation.arc.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;
import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.implementation.aws.AwsService;
import com.chavaillaz.awsec2utils.api.implementation.common.AuthService_A;
import com.chavaillaz.awsec2utils.api.model.VmTemplate;
import com.chavaillaz.awsec2utils.api.specification.arc.service.ListService_I;
import com.chavaillaz.awsec2utils.api.specification.aws.service.TagInstanceService_I;
import com.chavaillaz.awsec2utils.utils.VmState;

/**
 * Implementation of {@link ListService_I}
 * 
 * @author Johan Chavaillaz
 */
public class ListService extends AuthService_A implements ListService_I {
	
	private static final Logger logger = LogManager.getLogger(ListService.class);

	public ListService(AmazonEC2Client aws) {
		super(aws);
	}

	public List<VmTemplate> list() throws Exception {
		logger.info("Getting VM template list ...");
		List<VmTemplate> listTemplate = new ArrayList<VmTemplate>();
		
		File folder = new File(Constants.VM_FOLDER);
		if (folder.exists() && folder.isDirectory()) {
			for (File fileEntry : folder.listFiles()) {
				String vmId = FilenameUtils.getBaseName(fileEntry.getPath());
				listTemplate.add(VmTemplate.getTemplate(vmId));
			}
		}
		
		return listTemplate;
	}

	public List<VmTemplate> listWithState() throws Exception {
		List<VmTemplate> listTemplate = list();
		logger.info("Getting instances information ...");
		List<Instance> listInstance = AwsService.getInstance().getListInstanceService(aws).getListInstance();
		Map<String, Instance> mapInstance = new HashMap<String, Instance>();
		
		TagInstanceService_I tagInstanceService = AwsService.getInstance().getTagInstanceService(aws);
		for (Instance instance:listInstance) {
			Tag tag = tagInstanceService.getTag(Constants.TAG_KEY, instance.getTags());
			if (mapInstance.containsKey(tag.getValue())) {
				if (VmState.getSignificantNumber(instance) < VmState.getSignificantNumber(mapInstance.get(tag.getValue()))) {
					mapInstance.put(tag.getValue(), instance);
				}
			} else {
				mapInstance.put(tag.getValue(), instance);
			}
		}
		
		for (VmTemplate template:listTemplate) {
			template.setInstance(mapInstance.get(template.getVmId()));
		}
		
		return listTemplate;
	}

	public List<VmTemplate> listState(String state) throws Exception {
		List<VmTemplate> listTemplateWithState = new ArrayList<VmTemplate>();
		for (VmTemplate template: listWithState()) {
			if (template.hasState() && template.getState().equals(state)) { 
				listTemplateWithState.add(template);
			}
		}
		
		return listTemplateWithState;
	}

}
