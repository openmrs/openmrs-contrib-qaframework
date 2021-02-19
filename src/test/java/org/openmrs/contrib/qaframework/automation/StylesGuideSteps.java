package org.openmrs.contrib.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openmrs.contrib.qaframework.page.StylesGuidePage;
import org.openmrs.reference.page.HomePage;
import org.openmrs.reference.page.SystemAdministrationPage;
import org.openqa.selenium.By;

public class StylesGuideSteps extends Steps {
	private SystemAdministrationPage systemAdministrationPage;

	private StylesGuidePage stylesGuidePage;

	@After("@selenium")
	public void destroy() {
		quit();
	}

	@Given("a user logins into the system")
	public void loginForStyles() {
		goToLoginPage();
		loginPage.login(testProperties.getUsername(),
				testProperties.getPassword(), testProperties.getLocation());
		homePage = new HomePage(loginPage);
	}

	@And("user navigates to the systems admin page")
	public void navigateToSystemsPage() {
		elementClickOn(By
				.id("coreapps-systemadministration-homepageLink-coreapps-systemadministration-homepageLink-extension"));
		systemAdministrationPage = new SystemAdministrationPage(homePage);
	}

	@When("user presses the styles guide link")
	public void loadStylesGuide() {
		elementClickOn(StylesGuidePage.STYLES_GUIDE_LINK);
		stylesGuidePage = new StylesGuidePage(systemAdministrationPage);
	}

	@Then("system should load the styles guide page")
	public void validatePage() {
		Assert.assertNotNull(getElement(StylesGuidePage.STYLES_GUIDE_HEADER));
	}

	@And("user clicks back")
	public void back() {
		stylesGuidePage.pressBack();
	}

	@Then("system should return to the previous page")
	public void validateReturn() {
		Assert.assertNotNull(StylesGuidePage.STYLES_GUIDE_LINK);
	}

}
