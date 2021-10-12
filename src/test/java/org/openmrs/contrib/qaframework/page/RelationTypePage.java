package org.openmrs.contrib.qaframework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by tomasz on 15.07.15.
 */
public class RelationTypePage extends AdminManagementPage {

	static final By DELETE_RELATION_TYPE = By.name("purge");
	static final By RETIRE_RELATION_TYPE = By.name("retire");
	static final By A_IS_TO_B = By.name("aIsToB");
	static final By B_IS_TO_A = By.name("bIsToA");
	static final By DESCRIPTION = By.name("description");
	static final By ERROR = By.cssSelector("span.error");

	public RelationTypePage(WebDriver driver) {
		super(driver);
		MANAGE = By.linkText("Manage Relationship Types");
		ADD = By.linkText("Add Relationship Type");
		SAVE = By.name("save");
		RETIRE = By.name("retire");
	}

	public void fillInRelationTypeAIsToB(String name) {
		fillInField(findElement(A_IS_TO_B), name);
	}

	public void fillInRelationTypeBIsToA(String name) {
		fillInField(findElement(B_IS_TO_A), name);
	}

	public void createRelationType(String aistob, String bistoa,
			String description) throws InterruptedException {
		fillInRelationTypeAIsToB(aistob);
		fillInRelationTypeBIsToA(bistoa);
		fillInDescription(description);
		save();
	}

	public void waitForError() {
		waitForElement(ERROR);
	}

	@Override
	public String getPageUrl() {
		return "/admin/person/relationshipType.list";
	}
}
