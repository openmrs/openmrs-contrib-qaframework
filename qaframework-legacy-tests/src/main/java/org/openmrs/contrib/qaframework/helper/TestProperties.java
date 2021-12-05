/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.helper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

/**
 * Exposes test properties. This class is typically used like this:
 * TestProperties properties = TestProperties.instance();
 * 
 * Properties are obtained using the following order of lookup: 1. Java system
 * property (System.getProperty). This allows for command line -D setting. 2. OS
 * environment variable (System.getenv). 3. test.properties file found on the
 * classpath under org/openmrs/uitestframework/ 4. The hard-wired defaults in
 * this class. Also note that test.properties can be "filled in" with properties
 * from pom.xml by using ${}, e.g. in test.properties: webapp.url=${webapp.url}
 * in pom.xml: &lt;properties&gt;
 * &lt;webapp.url&gt;http://localhost:8080/openmrs&lt;/webapp.url&gt;
 * &lt;/properties&gt;
 */
public class TestProperties {

	public static final String WEBDRIVER_PROPERTY = "webdriver";

	public static final String DEFAULT_WEBDRIVER = "firefox";

	public static final String LOGIN_PASSWORD_PROPERTY = "login.password";

	public static final String DEFAULT_PASSWORD = "test";

	public static final String LOGIN_USERNAME_PROPERTY = "login.username";

	public static final String DEFAULT_LOGIN_USERNAME = "admin";

	public static final String WEBAPP_URL_PROPERTY = "webapp.url";

	public static final String DEFAULT_WEBAPP_URL = "http://localhost:8080/openmrs";

	public static final String AUTO_LOGIN_AT_STARTUP_PROPERTY = "login.auto";

	public static final String LOGIN_LOCATION_PROPERTY = "login.location";

	public static final String DEFAULT_LOGIN_LOCATION = "Outpatient Clinic";

	public static final String HEADLESS_PROPERTY = "headless";

	public static final String DB_HOST = "db.host";

	public static final String DEFAULT_HEADLESS = "false";

	private static TestProperties SINGLETON;

	private Properties properties;

	public TestProperties() {
		properties = new Properties();
		try {
			URL resource = Thread.currentThread().getContextClassLoader()
					.getResource("org/openmrs/uitestframework/test.properties");
			if (resource != null) {
				InputStream input = resource.openStream();
				properties.load(new InputStreamReader(input, "UTF-8"));
			}
		} catch (IOException ioException) {
			throw new RuntimeException("Could not find test.properties", ioException);
		}
	}

	public static TestProperties instance() {
		if (SINGLETON == null) {
			SINGLETON = new TestProperties();
		}
		return SINGLETON;
	}

	public String getWebAppUrl() {
		return getProperty(WEBAPP_URL_PROPERTY, DEFAULT_WEBAPP_URL);
	}

	public String getUsername() {
		return getProperty(LOGIN_USERNAME_PROPERTY, DEFAULT_LOGIN_USERNAME);
	}

	public String getPassword() {
		return getProperty(LOGIN_PASSWORD_PROPERTY, DEFAULT_PASSWORD);
	}

	public String getLocation() {
		return getProperty(LOGIN_LOCATION_PROPERTY, DEFAULT_LOGIN_LOCATION);
	}

	public String getHeadless() {
		return getProperty(HEADLESS_PROPERTY, DEFAULT_HEADLESS);
	}

	public String getDbHost() {
		return getProperty(DB_HOST, null);
	}

	public boolean automaticallyLoginAtStartup() {
		return Boolean.parseBoolean(getProperty(AUTO_LOGIN_AT_STARTUP_PROPERTY, "true"));
	}

	public String getBrowser() {
		return getProperty(WEBDRIVER_PROPERTY, DEFAULT_WEBDRIVER);
	}

	public WebDriverType getWebDriver() {
		try {
			return WebDriverType.valueOf(getBrowser());
		} catch (IllegalArgumentException e) {
			return WebDriverType.firefox;
		}
	}

	public String getProperty(String property, String defaultValue) {
		String value = System.getProperty(property);
		if (value == null) {
			value = System.getenv(property);
		}
		if (value == null) {
			value = properties.getProperty(property);
		}
		if (value == null) {
			value = defaultValue;
		}
		return value;
	}

	public String getFirefoxDriverLocation() {
		return getProperty("webdriver.gecko.driver", null);
	}

	public enum WebDriverType {
		chrome, firefox
	}
}
