package org.openmrs.contrib.qaframework.page;

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;

public class ManageReportsPage extends Page {
	public static final By RUN_BUTTON = By
			.xpath("//*[@id=\"container\"]/div/table/tbody/tr[12]/td[6]/a[3]");

	public ManageReportsPage(Page page) {
		super(page);
	}

	public RunReportPage clickOnRunButton() {
		clickOn(RUN_BUTTON);
		return new RunReportPage(this);
	}

	@Override
	public String getPageUrl() {
		return "/reporting/reports/manageReports.form";
	}
}
