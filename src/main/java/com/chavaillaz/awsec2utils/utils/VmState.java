package com.chavaillaz.awsec2utils.utils;

import com.amazonaws.services.ec2.model.Instance;

/**
 * Utility class to manage virtual machine state.
 * 
 * @author Johan Chavaillaz
 */
public class VmState {
	
	public static final String PENDING = "pending";
	public static final Integer PENDING_CODE = 0;
	
	public static final String RUNNING = "running";
	public static final Integer RUNNING_CODE = 16;

	public static final String SHUTTING_DOWN = "shutting-down";
	public static final Integer SHUTTING_DOWN_CODE = 32;

	public static final String TERMINATED = "terminated";
	public static final Integer TERMINATED_CODE = 48;

	public static final String STOPPING = "stopping";
	public static final Integer STOPPING_CODE = 64;
	
	public static final String STOPPED = "stopped";
	public static final Integer STOPPED_CODE = 80;
	
	public static boolean isPending(Instance instance) {
		if (instance == null) return false;
		return instance.getState().getCode() == PENDING_CODE;
	}
	
	public static boolean isRunning(Instance instance) {
		if (instance == null) return false;
		return instance.getState().getCode() == RUNNING_CODE;
	}
	
	public static boolean isShuttingDown(Instance instance) {
		if (instance == null) return false;
		return instance.getState().getCode() == SHUTTING_DOWN_CODE;
	}
	
	public static boolean isTerminated(Instance instance) {
		if (instance == null) return true;
		return instance.getState().getCode() == TERMINATED_CODE;
	}
	
	public static boolean isStopping(Instance instance) {
		if (instance == null) return false;
		return instance.getState().getCode() == STOPPING_CODE;
	}
	
	public static boolean isStopped(Instance instance) {
		if (instance == null) return false;
		return instance.getState().getCode() == STOPPED_CODE;
	}
	
	public static boolean isUnstableState(Instance instance) {
		if (instance == null) return false;
		return isUnstableState(instance.getState().getCode());
	}
	
	public static boolean isUnstableState(Integer code) {
		return code == PENDING_CODE || code == SHUTTING_DOWN_CODE || code == STOPPING_CODE;
	}
	
	public static Integer getSignificantNumber(Instance instance) {
		if (instance == null) return 100; // Least significant
		switch (instance.getState().getCode()) {
			case 0:
				return 0;
			case 16:
				return 1;
			case 32:
				return 4;
			case 48:
				return 5;
			case 64:
				return 2;
			case 80:
				return 3;
			default:
				return 100; // Least significant
		}
	}

}
