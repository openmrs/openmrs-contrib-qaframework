package org.openmrs.contrib.qaframework.page;

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;

public class ReportHistoryPage extends Page {

	private static final By VIEW_REPORT = By.xpath("//*[@id=\"container\"]/table/tbody/tr/td[2]/div[1]/a");
	
	public ReportHistoryPage(Page page) {
		super(page);
	}

	public RenderDefaultReportPage clickOnViewLink() {
		clickOn(VIEW_REPORT);
		return new RenderDefaultReportPage(this);
	}

	@Override
	public String getPageUrl() {
		return "/reporting/reports/reportHistoryOpen";
	}

}
