package org.openmrs.contrib.qaframework.page;

import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.uitestframework.page.Page;
import org.openqa.selenium.By;

public class ConditionsPage extends Page {
	public static final By MANAGE_CONDITIONS_ENDPOINT = By
			.cssSelector(".conditions .info-header i.right");
	private static final By RETURN = By.cssSelector(".actions .cancel");

	public ConditionsPage(
			ClinicianFacingPatientDashboardPage clinicianFacingPatientDashboardPage) {
		super(clinicianFacingPatientDashboardPage);
	}

	@Override
	public String getPageUrl() {
		return "/coreapps/conditionlist/manageConditions.page";
	}

	public ClinicianFacingPatientDashboardPage clickReturn() {
		clickOn(RETURN);
		return new ClinicianFacingPatientDashboardPage(this);
	}
}
