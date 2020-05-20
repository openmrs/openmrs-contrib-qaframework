package org.openmrs.contrib.qaframework.page;

import org.openmrs.uitestframework.page.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class AddNewConditionPage extends Page {
    private static final By CANCEL = By.className("cancel");
    public static final By SAVE = By.id("addConditionBtn");
    private static final By CONDITION = By.id("conceptId-input");
    private static final By DATE = By.cssSelector(".date:first_child");
    private static final By START_DATE = By.name("conditionStartDate");
    private static final By ACTIVE = By.id("status-1");
    private static final By INACTIVE = By.id("status-2");

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
        WebElement condition = findElement(CONDITION);
        condition.sendKeys(conditionText);
        condition.sendKeys(Keys.ENTER);
    }

    public void clickSave() {
        clickOn(SAVE);
    }

    public void clickOnActive() {
        clickOn(ACTIVE);
    }

    public void clickOnInActive() {
        clickOn(INACTIVE);
    }
}
