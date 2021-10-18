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

import org.openmrs.contrib.qaframework.helper.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by tomasz on 26.05.15.
 */
public class PatientCaptureVitalsPage extends Page {

	public static final String URL_PATH = "/htmlformentryui/htmlform/enterHtmlFormWithSimpleUi.page";
	private static final By HEIGHT_FIELD = By.id("w8");
	private static final By WEIGHT_FIELD = By.id("w10");
	private static final By TEMPERATURE_FIELD = By.id("w12");
	private static final By PULSE_FIELD = By.id("w14");
	private static final By RESPIRATORY_FIELD = By.id("w16");
	private static final By BLOOD_PRESSURE_FIELD_1 = By.id("w18");
	private static final By BLOOD_PRESSURE_FIELD_2 = By.id("w20");
	private static final By BLOOD_OXYGEN_SATURATION_FIELD = By.id("w22");
	private static final By CONFIRM_BUTTON = By.xpath("//ul[@id='formBreadcrumb']/li[2]/span");
	private static final By CONFIRM_BUTTON_2 = By.id("coreapps-vitals-confirm");
	private static final By SAVE_BUTTON = By.xpath("//button[@type='submit']");

	public PatientCaptureVitalsPage(Page page) {
		super(page);
	}

	@Override
	public String getPageUrl() {
		return URL_PATH;
	}

	public void clearPatientHeight() {
		findElement(HEIGHT_FIELD).clear();
	}

	public void setHeightField(String value) {
		WebElement heightField = findElement(HEIGHT_FIELD);
		heightField.clear();
		heightField.sendKeys(value);
		findElement(By.xpath("//ul[@id='formBreadcrumb']/li/ul/li[2]")).click();
	}

	public void clearPatientWeight() {
		findElement(WEIGHT_FIELD).clear();
	}

	public void setWeightField(String value) {
		WebElement weightField = findElement(WEIGHT_FIELD);
		weightField.clear();
		weightField.sendKeys(value);
		findElement(By.xpath("//ul[@id='formBreadcrumb']/li/ul/li[4]")).click();
	}

	public void clearPatientTemperature() {
		findElement(TEMPERATURE_FIELD).clear();
	}

	public void setTemperatureField(String value) {
		WebElement temperatureField = findElement(TEMPERATURE_FIELD);
		temperatureField.clear();
		temperatureField.sendKeys(value);
		findElement(By.xpath("//ul[@id='formBreadcrumb']/li/ul/li[5]")).click();
	}

	public void clearPatientPulse() {
		findElement(PULSE_FIELD).clear();
	}

	public void setPulseField(String value) {
		WebElement pulseField = findElement(PULSE_FIELD);
		pulseField.clear();
		pulseField.sendKeys(value);
		findElement(By.xpath("//ul[@id='formBreadcrumb']/li/ul/li[6]")).click();
	}

	public void clearPatientRespiratoryRate() {
		findElement(RESPIRATORY_FIELD).clear();
	}

	public void setRespiratoryField(String value) {
		WebElement respiratoryField = findElement(RESPIRATORY_FIELD);
		respiratoryField.clear();
		respiratoryField.sendKeys(value);
		findElement(By.xpath("//ul[@id='formBreadcrumb']/li/ul/li[7]")).click();
	}

	public void clearPatientBloodPressure1() {
		findElement(BLOOD_PRESSURE_FIELD_1).clear();
	}

	public void clearPatientBloodPressure2() {
		findElement(BLOOD_PRESSURE_FIELD_2).clear();
	}

	public void setBloodPressureFields(String value1, String value2) {
		WebElement bloodPressureField1 = findElement(BLOOD_PRESSURE_FIELD_1);
		bloodPressureField1.clear();
		bloodPressureField1.sendKeys(value1);

		WebElement bloodPressureField2 = findElement(BLOOD_PRESSURE_FIELD_2);
		bloodPressureField2.clear();
		bloodPressureField2.sendKeys(value2);
		findElement(By.xpath("//ul[@id='formBreadcrumb']/li/ul/li[8]")).click();
	}

	public void clearPatientBloodOxygenSaturation() {
		findElement(BLOOD_OXYGEN_SATURATION_FIELD).clear();
	}

	public void setBloodOxygenSaturationField(String value) {
		WebElement bloodOxygenSaturationField = findElement(BLOOD_OXYGEN_SATURATION_FIELD);
		bloodOxygenSaturationField.clear();
		bloodOxygenSaturationField.sendKeys(value);
		findElement(By.xpath("//ul[@id='formBreadcrumb']/li/ul/li[2]")).click();
	}

	public void confirm() {
		WebElement confirmButton = findElement(CONFIRM_BUTTON);
		confirmButton.click();
	}

	public boolean save() {
		try {
			WebElement saveButton = findElement(SAVE_BUTTON);
			saveButton.click();

			findElementById("info-message").getText().contains("Entered Vitals");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void checkIfRightPatient() {
		WebElement confirmButton2 = findElement(CONFIRM_BUTTON_2);
		// WebDriverWait waiter = new WebDriverWait(driver, 30L);
		if (confirmButton2 != null) {
			// waiter.until(ExpectedConditions.elementToBeClickable(CONFIRM_BUTTON_2));
			confirmButton2.click();
		}
	}
}
