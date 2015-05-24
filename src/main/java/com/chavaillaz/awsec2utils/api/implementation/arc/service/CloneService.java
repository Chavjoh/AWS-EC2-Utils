package com.chavaillaz.awsec2utils.api.implementation.arc.service;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.api.model.VmTemplate;
import com.chavaillaz.awsec2utils.api.specification.arc.service.CloneService_I;

/**
 * Implementation of {@link CloneService_I}
 * 
 * @author Johan Chavaillaz
 */
public class CloneService implements CloneService_I {
	
	private static final Logger logger = LogManager.getLogger(ConfigService.class);

	public void clone(String newVmId, String vmId) throws IOException {
		if (!VmTemplate.exists(vmId) || !VmTemplate.exists(newVmId)) {
			logger.error("VM Template not found. Please create one with <define> command.");
		}
		
		logger.info("Cloning");
		Files.copy(
				Paths.get(Constants.VM_FOLDER + vmId + Constants.VM_EXTENSION), 
				Paths.get(Constants.VM_FOLDER + newVmId + Constants.VM_EXTENSION), 
				REPLACE_EXISTING);
	}

}
