package org.openmrs.contrib.qaframework.automation;

import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.page.AdministrationPage;
import org.openmrs.contrib.qaframework.page.ManageReportsPage;
import org.openmrs.contrib.qaframework.page.RenderDefaultReportPage;
import org.openmrs.contrib.qaframework.page.ReportHistoryPage;
import org.openmrs.contrib.qaframework.page.RunReportPage;
import org.openmrs.contrib.qaframework.page.SystemAdministrationPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReportSteps extends Steps {

	private SystemAdministrationPage systemAdministrationPage;
	private AdministrationPage administrationPage;
	private ManageReportsPage manageReportsPage;
	private ReportHistoryPage reportHistoryPage;
	private RunReportPage runReportsPage;
	private RenderDefaultReportPage renderDefaultReportPage;
	private static final String startDate = "05 / 07 / 2008";
	private static final String endDate = "20 / 06 / 2020";

	@Before(RunTest.HOOK.SELENIUM_REPORT)
	public void visitHomePage() {
		initiateWithLogin();
	}

	@After(RunTest.HOOK.SELENIUM_REPORT)
	public void destroy() {
		quit();
	}

	@Given("a user go to system administartion app")
	public void clickOnSystemAdministrationPage() {
		systemAdministrationPage = homePage.goToSystemAdministrationPage();
	}

	@Then("the system loads system administrationpage")
	public void LoadsSystemAdministrationPage() {
		assertPage(systemAdministrationPage.waitForPage());
	}

	@When("user clicks on advanced administration page")
	public void goToAdministrationPage() {
		administrationPage = systemAdministrationPage
				.goToAdvancedAdministration();
	}

	@And("user clicks on report administration link")
	public void clicksOnReportAdministrationPage() {
		manageReportsPage = administrationPage
				.clickOnReportAdministrationLink();
	}

	@Then("the system loads manage reports page")
	public void loadManageReportsPage() {
		assertPage(manageReportsPage.waitForPage());
	}

	@And("user clicks on run button from manage Reports page")
	public void clicksOnRunButton() {
		runReportsPage = manageReportsPage.clickOnRunButton();
	}

	@Then("user enters start date")
	public void userEntersStartDate() {
		runReportsPage.enterStartDate(startDate);
		runReportsPage.clickOnEmptyForm();
	}

	@Then("user enters end date")
	public void userEntersEndDate() {
		runReportsPage.enterEndDate(endDate);
	}

	@And("user clicks on request report button")
	public void userclicksOnRequestReport() {
		reportHistoryPage = runReportsPage.clickOnRequestReport();
	}

	@And("user clicks on view report link")
	public void userClicksOnViewReport() {
		renderDefaultReportPage = reportHistoryPage.clickOnViewLink();
	}

}
