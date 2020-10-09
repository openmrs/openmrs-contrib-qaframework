package org.openmrs.contrib.qaframework.page;

import org.openmrs.uitestframework.page.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

public class AllergyPage extends Page {

	private static final By  ALLERGIESLIST = By.id("allergies");

	public AllergyPage(Page page) {
		super(page);
	}

	public Dimension getAllergiesListSize(){
		WebElement allergiesList = driver.findElement(ALLERGIESLIST);
		allergiesList.getSize();
		return allergiesList.getSize();
	}


	@Override
	public String getPageUrl() {
		return "/allergyui/allergies.page";
	}
}
