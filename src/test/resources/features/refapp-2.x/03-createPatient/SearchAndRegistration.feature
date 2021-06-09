Feature: Search And Patient Registration

  Background:
    Given User logs in as Admin
    Given System loads back to HomePage

  @Selenium
  @searchAndRegistration
  Scenario: searching for a patient
    And User clicks on Find Patient Record App
    Then User searches john smith
    And  User click on first Patient
    Then Load patient dashboard

  @selenium
  @searchAndRegistration
  Scenario: register new Patient
    And User click on Rgistration app
    And User enters Patient details
    Then User click on confirm button

