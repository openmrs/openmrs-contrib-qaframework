package org.openmrs.contrib.qaframework.automation;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.commons.lang3.RandomStringUtils;
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.page.AppointmentSchedulingPage;
import org.openmrs.contrib.qaframework.page.ManageServiceTypesPage;
import org.openmrs.contrib.qaframework.page.ServicePage;

public class ServiceSteps extends Steps {

    private AppointmentSchedulingPage appointmentSchedulingPage;
    private ManageServiceTypesPage manageServiceTypesPage;
    private ServicePage servicePage;
    
    private String name;
    private String duration;
    private String description;
    
    @Before(RunTest.HOOK.SELENIUM_SERVICE)
    public void setUp() throws Exception {
        initiateWithLogin();
        name = RandomStringUtils.randomAlphabetic(5);
        duration = RandomStringUtils.randomNumeric(2);
        description = RandomStringUtils.randomAlphabetic(10);
    }

    @After(RunTest.HOOK.SELENIUM_SERVICE)
    public void destroy() {
        quit();
    }

    @Given("user goes to the appointment scheduling app")
    public void goToAppointmentScheduling() {
        appointmentSchedulingPage = homePage.goToAppointmentScheduling();
    }

    @When("user goes to manage service page")
    public void goToManageService() {
        manageServiceTypesPage = appointmentSchedulingPage.goToManageServices();
    }

    @Then("user clicks on new service page")
    public void userClicksOnNewService() {
        servicePage = manageServiceTypesPage.clickOnNewServiceType(); 
    }
    
    @And("user enters service details")
    public void userEntersServiceDetails() {
        servicePage.setName(name);
        servicePage.setDuration(duration);
        servicePage.setDescription(description);
    }

    @And("user clicks on save button")
    public void userClicksOnSaveButton() {
        manageServiceTypesPage = servicePage.save();
    }

    @And("system checks the saved service")
    public void systemChecksSavedService() {
        assertThat(manageServiceTypesPage.getServiceType(name), is(true));
    }

    @And("user clicks on edit service type icon")
    public void userClicksOnEditServiceType() {
        servicePage = manageServiceTypesPage.editServiceType(name);
        servicePage.setName(name + name);
    }

    @And("user clicks again on save button")
    public void userClicksAgainOnSave(){
        manageServiceTypesPage = servicePage.save();
    }

    @And("system checks the editted service type")
    public void checkEdittedService() {
        assertThat(manageServiceTypesPage.getServiceType(name + name), is(true));
    }

    @And("user clicks on delete service type icon")
    public void userClicksOnDeleteServiceType() throws Exception {
        manageServiceTypesPage.deleteServiceType(name + name);
    }

    @And("user clicks on comfirm button")
    public void userClicksOnConfirmDeleteButton() {
        manageServiceTypesPage.confirmDelete();
    }

    @Then("user checks the availability of service type by name")
    public void checkAvailabilityOfServiceTypeByName() {
        assertThat(manageServiceTypesPage.getServiceType(name), is(false));
    }

}
