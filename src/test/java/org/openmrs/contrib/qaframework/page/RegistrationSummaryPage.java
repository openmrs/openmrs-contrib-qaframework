package org.openmrs.contrib.qaframework.page;

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;

public class RegistrationSummaryPage extends Page {

	private static final By EDIT_RELATIONSHIP_LINK = By
			.id("relationships-info-edit-link");

	public RegistrationSummaryPage(Page parent) {
		super(parent);
	}

	public EditPatientRelationshipPage goToEditPatientRelationship() {
		clickOnLast(EDIT_RELATIONSHIP_LINK);
		return new EditPatientRelationshipPage(this);
	}

	@Override
	public String getPageUrl() {
		return "/registrationapp/registrationSummary.page";
	}
}
