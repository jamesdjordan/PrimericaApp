package com.ps.primerica.config;

public class EnvironmentUtils {

	public static String readPropertyOrEnv(String key) {
		return readPropertyOrEnv(key, null);
	}
	    
	public static String readPropertyOrEnv(String key, String defaultValue) {
		String v = System.getProperty(key);
	    if (v == null)
	    	v = System.getenv(key);
	    if (v == null)
	    	v = defaultValue;
	    return v;
	}	
}
