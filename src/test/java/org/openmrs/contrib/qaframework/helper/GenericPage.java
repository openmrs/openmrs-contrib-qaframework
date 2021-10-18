package org.openmrs.contrib.qaframework.helper;

import org.openqa.selenium.WebDriver;

/**
 * A Page that represents any page, i.e. a page that we don't (yet) know which
 * page it is.
 */
public class GenericPage extends Page {

	public GenericPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String getPageUrl() {
		return null;
	}

}
