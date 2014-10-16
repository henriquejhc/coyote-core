package org.coyote.core.application;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CoyoteConfiguration {

	public static final Logger logger = LogManager.getLogger(CoyoteConfiguration.class.getName());

	private static final String FILE_COYOTE_CONFIG_PROPERTIES = "/coyote-config.properties";
	
	private static CoyoteConfiguration INSTANCE = new CoyoteConfiguration();

	private Properties properties;

	private CoyoteConfiguration() {
		logger.debug("::-->> Initializing CoyoteConfiguration.....");
		try {
			this.properties = new Properties();
			loadProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadProperties() throws IOException {
		logger.debug("::-->> Loading file " + FILE_COYOTE_CONFIG_PROPERTIES + " ");
		InputStream inputStream = getClass().getResourceAsStream(FILE_COYOTE_CONFIG_PROPERTIES);  
		this.properties.load(inputStream);  
        inputStream.close();            
	}

	public String getString(String key) {
		logger.debug("::-->> CoyoteConfiguration->getString: " + key);
		return this.properties.getProperty(key);
	}

	public static CoyoteConfiguration getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CoyoteConfiguration();
		}
		return INSTANCE;
	}

}
