package org.openmrs.contrib.qaframework.page;

import java.util.List;
import org.openmrs.uitestframework.page.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Attachmentspage extends Page {

    public static final By ATTACHEMENT = By.id("attachments.attachments.visitActions.default");
    public static final By CLICKORDROPAFILE = By.id("visit-documents-dropzone");
    public static final By CAPTION = By.xpath("//textarea[contains(@placeholder, 'Enter a caption')]");
    public static final By CLEARFORMS = By.xpath("//button[@type='submit' and contains(.,'Clear forms')]");
    public static final By UPLOAD = By.xpath("//button[@type='submit' and contains(.,'Upload file')]");
    public static final By LISTOFFILE = By.xpath("//att-thumbnail");

    public Attachmentspage(Page parent) {
        super(parent);
    }

    public void ClickOnAttachement() {
        clickOn(ATTACHEMENT);
    }

    public void ClickOnClickOrDropAfile(){
        WebElement selectFile = driver.findElement(CLICKORDROPAFILE);
        selectFile.sendKeys("https://snipboard.io/xquHf7.jpg");

    }

    public void setCaption(){
        driver.findElement(CAPTION).sendKeys("test file");
    }

    public void Clearforms(){
        driver.findElement(CLEARFORMS).click();
    }

    public void clickOnUpload(){
        driver.findElement(UPLOAD).click();
    }

    public List<WebElement> getListOfFile(){
        return driver.findElements(LISTOFFILE);
    }

    public String getPageUrl() {
        return "attachments/attachments.page?";
    }
}
