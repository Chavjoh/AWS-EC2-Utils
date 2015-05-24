package com.chavaillaz.awsec2utils.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Utility class to manage exceptions.
 * 
 * @author Johan Chavaillaz
 */
public class ExceptionUtils {
	
	public static String getStackTrace(final Throwable throwable) {
		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw, true);
		throwable.printStackTrace(pw);
		return sw.getBuffer().toString();
	}

}
