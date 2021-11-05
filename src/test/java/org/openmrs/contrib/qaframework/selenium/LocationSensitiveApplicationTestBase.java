package org.openmrs.contrib.qaframework.selenium;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.openmrs.contrib.qaframework.helper.RestClient;
import org.openmrs.contrib.qaframework.helper.TestData;

public class LocationSensitiveApplicationTestBase extends ReferenceApplicationTestBase {

    private String locationUuid;
    private String locationName;

    @Before
    public void createTaggedLocation() {
        String visit = TestData.getLocationTag("Visit Location");
        String login = TestData.getLocationTag("Login Location");
        String transfer = TestData.getLocationTag("Transfer Location");
        String admission = TestData.getLocationTag("Admission Location");

        locationName = "Location" + TestData.randomSuffix();
        locationUuid = new TestData.TestLocation(locationName, Arrays.asList(visit, login, transfer, admission)).create();

        goToLoginPage().loginAsAdmin(locationName);
    }

    @After
    public void deleteTestLocation(){
        if(StringUtils.isNotBlank(locationUuid)) {
            RestClient.delete("location/" + locationUuid);
        }
    }

    public String getLocationName() {
        return locationName;
    }
}
