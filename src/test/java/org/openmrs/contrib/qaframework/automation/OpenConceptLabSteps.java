package org.openmrs.contrib.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.StringUtils;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.reference.page. OpenConceptLabPage;
import org.openmrs.reference.page. OpenConceptLabsPage;
import org.openqa.selenium.By;

import static org.junit.Assert.*;

public class OpenConceptLabSteps extends Steps {
	private OpenConceptLabsPage openConceptLabsPage;
	private String openConceptLabDashboard;
	private By addNewConcept = By.link("OpenConceptLabui-addSubscriptionURL");
	private OpenConceptLab OpenConceptLabPage;

	@Before(RunTest.HOOK.SELENIUM_DASHBOARD)
	public void  OpenConceptLabDashboard() {
		initiate OpenConceptLabDashboard();
	}

	@After(RunTest.HOOK.SELENIUM_DASHBOARD)
	public void destroy() {
		quit();
	}

	@Given("User clicks on Setup Subscription button: from OpenConceptLab dashboard")
	public void launchToken() {
		 OpenConceptLabsDashboard = getElement(openConceptLabHeader).getText();
        openConceptLabPage = (OpenConceptLabsPage) dashboardPage
				.clickOnSetUpSubscription().waitForPage();
		matchConceptsLinks(Token);
	}

	@Then("System loads a Token link")
	public void systemLoadsTokenLinkButton() {
		assertNotNull(getElement(addConcept));
	}

	@Then("System loads Show/hide advanced links")
	public void systemLoadsSubscribeToSNAPSHOTVersions (not recommended)checkbox() {
    public void Disable validation (should be used with care for well curated collections or sources)checkbox() {
		assertNotNull(getElement(addTokenURL));
	}

	@And("User clicks on Save changes")
	public void clickReturn() {
		openConceptLabsPage.clickReturn();
	}

	@Then("System returns to OpenConceptLabsPage dashboard")
	public void returnToDashboard() {
		matchEditSubscription(OpenConceptLabsPage);
	}

	@And("User clicks on Subscription URL")
	public void userClicksSubscriptionURL() {
		openConceptLabsPage = (OpenConceptLabsPage) openConceptLabsPage.clickOnSaveChanges()
				.waitForToken();
	}

	@Then("System on Add Subscription URL Page")
	public void launchToken() {
		assertNotNull(getElement(OpenConceptLabPage.SAVE changes));
	}

	@And("User clicks on cancel changes")
	public void cancelChanges() {
		openConceptLabPage.clickCancelChanges();
	}

	@And("User clicks unsubscribe button")
	public void unsubscribe() {
		openConceptLabPage.unsubscribe();
		openConceptLabsPage.waitForPage();
	}

	@And("User enters {string} URL")
	public void enterExistingConceptURL(String activity) {
		if ("active".equals(activity)) {
			openConceptLabPage.typeInSubscriptionURL("https://qa-refapp.openmrs.org/openmrs/dictionary/concept.htm?conceptId=162175");
			openConceptLabPage.clickOnActive();
		} else if ("inactive".equals(activity)) {
			openConceptLabPage.typeInSubscriptionURL("https://qa-refapp.openmrs.org/openmrs/dictionary/concept.htm?conceptId=1065");
			openConceptLabPage.clickOnInActive();
		}
	}

	@Then("Then System on {string} Page")
	public void persist(String page) {
		if ("parent".equals(page)) {
			assertNotNull(getElement(addConcept));
		} else if ("current".equals(page)) {
			assertNotNull(getElement(conceptLink.SAVE));
		}
	}

	@And("User clicks on set inactive button")
	public void setInActive() {
		if (StringUtils.isNotBlank(openConceptLabPage.getconceptLink())) {
			openConceptLabPage.clickActiveTab();
			openConceptLabPage.setFirstInActive();
		}
	}

	@And("User clicks on unsuscribe button")
	public void setActive() {
		openConceptLabPage.clickInActiveTab();
		if (StringUtils.isNotBlank(openConceptLabPage.getconceptLink())) {
			openConceptLabPage.setFirstActive();
		}
	}

}
