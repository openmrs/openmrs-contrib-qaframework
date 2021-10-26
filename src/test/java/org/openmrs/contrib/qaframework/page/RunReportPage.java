package org.openmrs.contrib.qaframework.page;

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RunReportPage extends Page {

	private static final By START_DATE_FIELD = By.cssSelector("#userEnteredParamstartDate");
	private static final By END_DATE_FIELD = By.cssSelector("#userEnteredParamendDate");
	private static final By REQUEST_REPORT_BUTTON = By.xpath("//*[@id=\"container\"]/div/table/tbody/tr/td[1]/fieldset/form/table[2]/tbody/tr[3]/td/input");
	private static final By CLOSE_TAB = By.xpath("//span[contains(text(),'close')]");
	private static final By VIEW_REPORT = By.xpath("//*[@id=\"container\"]/table/tbody/tr/td[2]/div[1]/a");
	private static final By VIEW = By.xpath("//*[@id=\"content\"]");

	public RunReportPage(Page page) {
		super(page);

	}

	public void enterStartDate(String startDate) {
		WebElement element = findElement(START_DATE_FIELD);
		clickOn(START_DATE_FIELD);
		waiter.until(ExpectedConditions.elementToBeClickable(START_DATE_FIELD));
		element.sendKeys("05 / 07 / 2008");
	}

	public void enterEndDate(String endDate) {
		clickOn(END_DATE_FIELD);
		waiter.until(ExpectedConditions.elementToBeClickable(START_DATE_FIELD));
		setTextToFieldNoEnter(END_DATE_FIELD, endDate);
	}

	public ReportHistoryPage clickOnRequestReport() {
		clickOn(REQUEST_REPORT_BUTTON);
		return new ReportHistoryPage(this);
	}

	public void clickOnView() {
		clickOn(VIEW);
	}
	public void clickOnCloseIcon() {
		clickOn(CLOSE_TAB);
	}

	@Override
	public String getPageUrl() {
		return "reporting/run/runReport";
	}
}