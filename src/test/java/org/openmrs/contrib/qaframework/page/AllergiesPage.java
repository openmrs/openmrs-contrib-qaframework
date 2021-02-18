package org.openmrs.contrib.qaframework.page;

import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.uitestframework.page.Page;
import org.openqa.selenium.By;

public class AllergiesPage extends Page {
    private static final By ALLERGIES_LINK = By.id("allergyui-editAllergies");
    private static final By ADD_NO_KNOWN = By
            .xpath("//*[contains(text(),'No Known Allergy')]");
    private static final By DELETE_NO_KNOWN = By.className("delete-action");

    public AllergiesPage(
            ClinicianFacingPatientDashboardPage clinicianFacingPatientDashboardPage) {
        super(clinicianFacingPatientDashboardPage);
    }

    @Override
    public String getPageUrl() {
        return "/allergyui/allergies.page";
    }

    // TODO move to ClinicianFacingPatientDashboardPage
    public void clickOnAllergiesAppLink() {
        driver.findElement(ALLERGIES_LINK).click();
    }

    public void addNoKnownAllergy() {
        driver.findElement(ADD_NO_KNOWN).click();
    }

    public void removeNoKnownAllergy() {
        driver.findElement(DELETE_NO_KNOWN).click();
    }

}
