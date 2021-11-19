package org.openmrs.contrib.qaframework.automation;
 
import static org.junit.Assert.assertNotNull;
 
import java.util.List;
 
import org.openmrs.contrib.qaframework.RunTest;
import org.openmrs.contrib.qaframework.page.ConfigureMetadataPage;
import org.openmrs.contrib.qaframework.page.OpenConceptLabPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
 
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 
public class OpenConceptLabSteps extends Steps  {
    private By subscriptionurl = By.id("subscription-url");
    private OpenConceptLabPage openconceptlabpage;
    private ConfigureMetadataPage configuremetadatapage;
    @Before(RunTest.HOOK.SELENIUM_OPEN_CONCEPT_LAB)
    public void visitHomePage() {
        initiateWithLogin();    
    }
    
    @After(RunTest.HOOK.SELENIUM_OPEN_CONCEPT_LAB)
    public void destroy(){
        quit();
    }
    
    @Given("User clicks on configure Metadata link from home page")
    public void launchMetadataDashboard() {
        configuremetadatapage = homePage.goToConfigureMetadata();
    }
 
    @When("User selects Manage OCL")
    public void manageOclPage() {
        openconceptlabpage = configuremetadatapage.goToManageOcl();
        //i have created goToManageOcl method in configureMetaDataPage as shown below
//      public OpenConceptLabPage goToManageOcl(){
//          clickOn(MANAGE_OCL_LINK);
//          return new OpenConceptLabPage(this);
    }
 
    @When("System loads Open Concept Lab page")
    public void systemLoadsOpenConceptPage() {
        assertNotNull(getElement(subscriptionurl));
    }
 
    @And ("User clicks on Setup subscription button")
    public void setupSubscription() {
        openconceptlabpage.clickOnsetupSubscription();
    }
 
    @And("the user enters the URL of a new released dictionary")
    public void enterDictionaryUrl() {
        openconceptlabpage.enterSubscriptionURL(releasedDictionaryUrl);
        releasedDictionaryUrl, you should something static like below
        //private static final String releasedDictionaryUrl = "fkhsjkudfhdbfhdbfh";
    }   
    
    @Then("the Token title is visible")
    public void tokenIsVisible(){
        openconceptlabpage.enterTokenURL(userUrl);
        //tokenUrl , you should something static like below
        //private static final String userUrl = "fkhsjkudfhdbfhdbfh";
    }
    
    @And ("User clicks on the Save Changes button")
    public void saveChanges(){
        openconceptlabpage.clickSaveButton();
    }
    
    @And ("the API should be displayed on the previous imports")
    public void displayAmongPreviousImports(){
        assertNotNull(openconceptlabpage.getpreviousImportsList());
        //i have added the following to the openConceptLabpage
//      public List<WebElement> getpreviousImportsList() {
//          return findElements(IMPORTS_LIST);
    }
}