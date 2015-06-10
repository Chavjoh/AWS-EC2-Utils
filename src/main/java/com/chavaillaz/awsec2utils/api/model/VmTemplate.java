package com.chavaillaz.awsec2utils.api.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.regex.Pattern;

import com.amazonaws.services.ec2.model.Instance;
import com.chavaillaz.awsec2utils.Constants;
import com.chavaillaz.awsec2utils.utils.StringShop;

/**
 * Virtual Machine Template file.
 * Used to create and manage instances on AWS EC2.
 * 
 * @author Johan Chavaillaz
 */
public class VmTemplate {
	
	public static final String VM_KEY = "vmId";
	public static final String IMAGE_KEY = "imageId";
	public static final String INSTANCE_TYPE_KEY = "instanceType";
	public static final String SECURITY_GROUP_KEY = "securityGroup";
	
	private Properties properties;
	private String vmId;
	private String imageId;
	private String instanceType;
	private String securityGroup;
	private Instance instance;

	/**
	 * Create a new Virtual Machine Template in memory.
	 * Use {@link #save} method to save it in properties file.
	 * 
	 * @param vmId Virtual Machine ID
	 * @param imageId Amazon Image ID
	 * @param instanceType Instance type
	 * @param securityGroup Security group name
	 * @throws Exception
	 */
	public VmTemplate(String vmId, String imageId, String instanceType, String securityGroup) throws Exception {
		super();
		
		this.properties = new Properties();
		this.vmId = vmId;
		this.imageId = imageId;
		this.instanceType = instanceType;
		this.securityGroup = securityGroup;
		
		Pattern patternId = Pattern.compile("[^a-zA-Z0-9_\\.-]");
		boolean hasSpecialChar = patternId.matcher(vmId).find();
		
		if (hasSpecialChar) {
			throw new Exception("Template ID format invalid.");
		}
	}

	/**
	 * Get VM Template from properties file.
	 * 
	 * @param vmId Virtual Machine ID
	 * @return Virtual Machine Template
	 * @throws Exception
	 */
	public static VmTemplate getTemplate(String vmId) throws Exception {
		if (!exists(vmId)) {
			return null;
		}
		
		Properties properties = new Properties();
		InputStream input = null;

		input = new FileInputStream(Constants.VM_FOLDER + vmId + Constants.VM_EXTENSION);
		properties.load(input);

		String imageId = properties.getProperty(IMAGE_KEY);
		String instanceType = properties.getProperty(INSTANCE_TYPE_KEY);
		String securityGroup = properties.getProperty(SECURITY_GROUP_KEY);

		input.close();
		
		return new VmTemplate(vmId, imageId, instanceType, securityGroup);
	}
	
	/**
	 * Check if the Virtual Machine Template exists.
	 * 
	 * @param vmId Virtual Machine ID
	 * @return Boolean representing if the VM Template exists
	 */
	public static boolean exists(String vmId) {
		File templateFile = new File(Constants.VM_FOLDER + vmId + Constants.VM_EXTENSION);
		return templateFile.exists() && !templateFile.isDirectory();
	}
	
	/**
	 * Save Virtual Machine Template to properties file.
	 * 
	 * @throws IOException
	 */
	public void save() throws IOException {
		File path = new File(getPath());
		File parent = path.getParentFile();
		
		if (!parent.exists() && !parent.mkdirs()){
		    throw new IllegalStateException("Couldn't create template directory " + parent);
		}
		
		OutputStream output = new FileOutputStream(path.getAbsolutePath());
		properties.setProperty(VM_KEY, vmId);
		properties.setProperty(IMAGE_KEY, imageId);
		properties.setProperty(INSTANCE_TYPE_KEY, instanceType);
		properties.setProperty(SECURITY_GROUP_KEY, securityGroup);
		properties.store(output, null);
		output.close();
	}

	/**
	 * Delete Virtual Machine Template properties file.
	 * 
	 * @return Boolean corresponding to state of the deletion
	 */
	public boolean delete() {
		File templateFile = new File(Constants.VM_FOLDER + vmId + Constants.VM_EXTENSION);
		
		if (templateFile.exists() && !templateFile.isDirectory()) {
			return templateFile.delete();
		} 
		
		return false;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) { return true; }
		if (object == null || object.getClass() != this.getClass()) { return false; }

		VmTemplate template = (VmTemplate)object;
		return vmId.equals(template.getVmId());
	}

	public String getVmId() {
		return vmId;
	}

	public void setVmId(String vmId) {
		this.vmId = vmId;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getInstanceType() {
		return instanceType;
	}

	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}

	public String getSecurityGroup() {
		return securityGroup;
	}
	
	public void setSecurityGroup(String securityGroup) {
		this.securityGroup = securityGroup;
	}
	
	public String getPath() {
		return Constants.VM_FOLDER + vmId + Constants.VM_EXTENSION;
	}
	
	public Instance getInstance() {
		return instance;
	}
	
	public void setInstance(Instance instance) {
		this.instance = instance;
	}
	
	public boolean hasInstance() {
		return (instance != null);
	}

	public boolean hasState() {
		return getState() != null;
	}

	public boolean hasDnsName() {
		return getDnsName() != null && !getDnsName().equals(StringShop.EMPTY);
	}

	public String getState() {
		if (instance == null) return null;
		return instance.getState().getName();
	}

	public String getDnsName() {
		if (instance == null) return null;
		return instance.getPublicDnsName();
	}
	
}
