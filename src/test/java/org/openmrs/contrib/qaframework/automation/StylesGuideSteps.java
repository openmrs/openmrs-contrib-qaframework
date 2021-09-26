package org.openmrs.contrib.qaframework.automation;

import org.junit.Assert;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.page.StylesGuidePage;
import org.openmrs.contrib.qaframework.page.SystemAdministrationPage;
import org.openqa.selenium.By;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StylesGuideSteps extends Steps {
	private SystemAdministrationPage systemAdministrationPage;

	private StylesGuidePage stylesGuidePage;

	@Before(RunTest.HOOK.SELENIUM_LOGIN)
	public void systemLogin() {
		initiateWithLogin();
	}

	@After(RunTest.HOOK.SELENIUM_LOGIN)
	public void destroy() {
		quit();
	}

	@Given("user navigates to the systems admin page")
	public void navigateToSystemsPage() {
		systemAdministrationPage = homePage.goToSystemAdministrationPage();
	}

	@And("user presses the styles guide link")
	public void loadStylesGuide() {
		stylesGuidePage = (StylesGuidePage) systemAdministrationPage
				.clickOnStylesGuideAppLink().waitForPage();
	}

	@Then("system should load the styles guide page")
	public void validatePage() {
		Assert.assertNotNull(getElement(StylesGuidePage.STYLES_GUIDE_HEADER));
	}

	@When("user clicks back")
	public void back() {
		stylesGuidePage.pressBack();
	}

	@Then("system should return to the previous page")
	public void validateReturn() {
		Assert.assertNotNull(By.className("icon-magic"));
	}

}
