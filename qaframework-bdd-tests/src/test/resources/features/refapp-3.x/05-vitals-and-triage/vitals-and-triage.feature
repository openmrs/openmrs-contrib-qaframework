Feature: Vitals and Triage

  Background:
    Given the user is logged in
    And the user arrives on a patient’s summary page

  @vitals-and-triage
    Scenario: The user should be able to update the demographics
      When the user clicks on Edit patient details
      Then the edit page should load 
      When the user updates the address
      Then the address should be updated

  @vitals-and-triage
    Scenario: The user should be able to record vitals
      When the user clicks on Record Vitals
      Then the Vitals form should load
      And the user adds vitals
      When the user adds abnormal vital signs
      Then the abnormal vital signs should show up as red
      When the user saves the form
      Then the vitals needs to be displayed on the Vitals table
