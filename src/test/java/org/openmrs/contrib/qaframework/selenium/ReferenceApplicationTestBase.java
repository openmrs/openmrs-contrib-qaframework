package org.openmrs.contrib.qaframework.selenium;

import org.junit.Before;
import org.openmrs.contrib.qaframework.helper.LoginPage;
import org.openmrs.contrib.qaframework.helper.Page;
import org.openmrs.contrib.qaframework.helper.TestBase;
import org.openmrs.contrib.qaframework.page.HomePage;
import org.openmrs.contrib.qaframework.page.ReferenceApplicationLoginPage;
import org.openqa.selenium.By;

/**
 * Each test class should extend ReferenceApplicationTestBase.
 *
 * Guidelines:
 * 1. Tests should be added under https://github.com/openmrs/openmrs-distro-referenceapplication/tree/master/ui-tests/src/test/java/org/openmrs/reference
 * 2. Each test class should be named starting with a verb, which best describes an action that is being tested, e.g. SearchActiveVisitsTest. By convention all test class names must end with Test.
 * 3. In general each class should contain one test method (annotated with @Test) and the test method should start with a verb and can provide a more detailed description of what is being tested than the class, e.g. searchActiveVisitsByPatientNameOrIdOrLastSeenTest.
 * 4. The test method should not visit more than 10 pages and should have 3-15 steps.
 * 5. You must not access Driver in a test. It is only allowed to perform actions calling methods in classes extending Page.
 * 6. Each test class should start from homePage and extend ReferenceApplicationTestBase.
 * 7. It is not allowed to instantiate classes extending Page in test classes. They must be returned from Page's actions e.g. ActiveVisitsPage activeVisitsPage = homePage.goToActiveVisitsSearch();
 * 8. Each page should have a corresponding class, which extends Page and it should be added under https://github.com/openmrs/openmrs-distro-referenceapplication/tree/master/ui-tests/src/main/java/org/openmrs/reference/page
 * 9. The page class should be named after page's title and end with Page.
 * 10. It is not allowed to call Driver's methods in a page. You should be calling methods provided by the Page superclass.
 *
 */
public class ReferenceApplicationTestBase extends TestBase {

    private static final By SELECTED_LOCATION = By.id("selected-location");
    protected HomePage homePage;

    public ReferenceApplicationTestBase() {
        super();
    }

    @Before
    public void before() {
        homePage = new HomePage(page);
    }

    public String getLocationUuid(Page page) {
        return driver.findElement(SELECTED_LOCATION).getAttribute("location-uuid");
    }
    
    @Override
    protected LoginPage getLoginPage() {
        return new ReferenceApplicationLoginPage(driver);
    }
}
