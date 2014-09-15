package com.epam.project.configuration;

import java.util.ResourceBundle;

public final class ConfigurationManager {
	private final static ResourceBundle resourceBundle = ResourceBundle
			.getBundle("/settings/PathSettings");

	private ConfigurationManager() {
	}

	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
