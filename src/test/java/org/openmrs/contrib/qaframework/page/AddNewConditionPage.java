package org.openmrs.contrib.qaframework.page;

import org.openmrs.uitestframework.page.Page;
import org.openqa.selenium.By;

public class AddNewConditionPage extends Page {
	private static final By CANCEL = By.className("cancel");
	public static final By SAVE = By.id("addConditionBtn");
	private static final By CONDITION = By.id("conceptId-input");
	private static final By ICON_CALENDAR = By
			.cssSelector(".date .icon-calendar");
	private static final By ICON_CALENDAR_TODATE = By
			.cssSelector("td.day.active");
	private static final By ACTIVE = By.id("status-1");
	private static final By INACTIVE = By.id("status-2");
	private static final By FIRST_CONDITION = By
			.cssSelector(".dropdown-menu:first-of-type li a:first-of-type");

	public AddNewConditionPage(ConditionsPage conditionsPage) {
		super(conditionsPage);
	}

	@Override
	public String getPageUrl() {
		return "/coreapps/conditionlist/addCondition.page";
	}

	public ConditionsPage clickCancel() {
		clickOn(CANCEL);
		return new ConditionsPage(this);
	}

	public void typeInCondition(String conditionText) {
		clickOn(CONDITION);
		findElement(CONDITION).sendKeys(conditionText);
		clickOn(FIRST_CONDITION);
	}

	public void clickSave() {
		clickOn(SAVE);
	}

	public void clickOnActive() {
		clickOn(ACTIVE);
	}

	public void clickOnInActive() {
		clickOn(INACTIVE);
		clickOn(ICON_CALENDAR);
		clickOn(ICON_CALENDAR_TODATE);
	}
}
