package org.openmrs.contrib.qaframework.page;

import java.util.Scanner;

import org.openmrs.reference.page.ClinicianFacingPatientDashboardPage;
import org.openmrs.uitestframework.page.Page;
import org.openqa.selenium.By;

public class AttachmentsPage extends Page {
	
	private static final By ATTACHMENTS_LINK = By.cssSelector("i.right:nth-child(1)");
	private static final By ATTACH_FILE = By.cssSelector(".dz-default");
	private static final By ADD_CAPTION = By
	        .xpath("/html/body/div/div[3]/div[3]/div[1]/att-file-upload/div[2]/div/div[2]/textarea");
	private static final By UPLOAD_FILE = By.cssSelector("button.confirm:nth-child(1)");
	private static final By CLEAR_FORMS = By.cssSelector("button.ng-binding:nth-child(2)");
	protected Scanner userInput = new Scanner(System.in);
	
	public AttachmentsPage(ClinicianFacingPatientDashboardPage clinicianFacingPatientDashboardPage) {
		super(clinicianFacingPatientDashboardPage);
	}
	
	@Override
	public String getPageUrl() {
		return "/attachments/attachments.page";
	}
	
	public void clickOnAttachmentLink() {
		driver.findElement(ATTACHMENTS_LINK).click();
	}
	
	public void clickOrDropFile() {
		driver.findElement(ATTACH_FILE).click();
	}
	
	public String addCaption() {
		String captionText = userInput.next();
		try {
			return driver.findElement(ADD_CAPTION).getAttribute(captionText);
		}
		catch (Exception ex) {
			return null;
		}
	}
	
	public void clickOnUploadFile() {
		driver.findElement(UPLOAD_FILE).click();
	}
	
	public void clickClearForms() {
		driver.findElement(CLEAR_FORMS).click();
	}
}
