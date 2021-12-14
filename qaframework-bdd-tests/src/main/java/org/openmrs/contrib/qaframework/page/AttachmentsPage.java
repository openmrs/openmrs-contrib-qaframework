/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * 
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.contrib.qaframework.page;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AttachmentsPage extends Page {

	private static final String URL = "/attachments/attachments.page";
	private static final By ATTACHMENTS_LIST = By.cssSelector("span.att_thumbnail-extension");
	private static final By ATTACHMENT_NOTE = By.cssSelector("textarea.ng-pristine");
	private static final By UPLOAD_FILE_BUTTON = By.cssSelector("button[ng-click='uploadFile()']");
	private static final By CLEAR_FORMS = By.cssSelector("button[ng-click='clearForms()']");
	private static final By CAMERA_ICON = By.cssSelector("button[ng-click='snap()']");
	private static final By SAVE_IMAGE = By.cssSelector("button[ng-click='finalise()']");
	private static final By RETURN_TO_DASHBOARD = By.xpath("//*[@id='breadcrumbs']/li[2]/a");

	public AttachmentsPage(Page page) {
		super(page);
	}

	public void setFileUrl(String fileUrl) {
		WebElement element = driver.findElement(By.xpath("//input[@type='file']"));
		element.sendKeys(fileUrl);
	}

	public void attachFile() throws IOException {
		setFileUrl(createTemporaryFile());
	}

	public void addAttachmentNote(String note) {
		findElement(ATTACHMENT_NOTE).clear();
		setText(ATTACHMENT_NOTE, note);
	}

	public void clickOnUploadFile() {
		clickOn(UPLOAD_FILE_BUTTON);
	}

	public void clickOnClearForms() {
		clickOn(CLEAR_FORMS);
	}

	public void clickOnCameraIcon() {
		clickOn(CAMERA_ICON);
	}

	public void clickOnSaveButton() {
		clickOn(SAVE_IMAGE);
	}

	public List<WebElement> getAttachmentsList() {
		return findElements(ATTACHMENTS_LIST);
	}

	public ClinicianFacingPatientDashboardPage goToPatientDashboardPage() {
		clickOn(RETURN_TO_DASHBOARD);
		return new ClinicianFacingPatientDashboardPage(this);
	}

	@Override
	public String getPageUrl() {
		return URL;
	}

	private String createTemporaryFile() throws IOException {
		File tempFile = File.createTempFile("file", ".pdf");
		tempFile.deleteOnExit();
		try (InputStream pdf = getClass().getClassLoader().getResourceAsStream("data/file.pdf")) {
			try (Writer writer = new FileWriter(tempFile)) {
				IOUtils.copy(pdf, writer, StandardCharsets.UTF_8);
			}
		}
		return tempFile.getAbsolutePath();
	}
}
