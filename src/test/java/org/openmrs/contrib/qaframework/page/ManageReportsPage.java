package org.openmrs.contrib.qaframework.page;

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;

public class ManageReportsPage extends Page {

	private static final By EDIT_REPORT_ICON = By.xpath("/html/body/div[1]/div[3]/div[3]/div/div/table/tbody/tr[12]/td[6]/a[1]/img");

	public ManageReportsPage(Page page) {
	    super(page);
	}

	public EditReportPage clickOnEditReportIcon() {
	    clickOn(EDIT_REPORT_ICON);
	    return new EditReportPage(this);
	}

	@Override
	public String getPageUrl() {
	    return "/reporting/reports/manageReports.form";
	}
}
