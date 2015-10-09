package com.ps.primerica.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {
	private static final String FILENAME = "application.properties";
	private static ApplicationProperties _applicationProperties;
	private Properties props = new Properties();
	
	private ApplicationProperties() {
		InputStream inStream = getClass().getClassLoader().getResourceAsStream(FILENAME);
		if (inStream != null) {
			try {
				props.load(inStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static ApplicationProperties getInstance() {
		if (_applicationProperties == null) {
			_applicationProperties = new ApplicationProperties();
		}
		return _applicationProperties;
	}
	
	public String getProperty(String key) {
		return props.getProperty(key);
	}
}
