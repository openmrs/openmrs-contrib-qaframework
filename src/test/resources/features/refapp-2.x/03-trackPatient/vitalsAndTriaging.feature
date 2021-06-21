Feature: Vitals Management

  Background:
    Given a user clicks on Capture Vitals link from Patient dashboard
    Then the system loads Vitals page

  @selenium
  @vitals
  Scenario: Normal Vitals
    And a user enters normal patient vitals
    And a user clicks on save button
    Then the system adds patient vitals into the vitals table 
   
  @selenium
  @vitals 
  Scenario: Abnormal Vitals
    And a user enters abnormal patient vitals
    And a user clicks on save button
    Then the system adds patient vitals into the vitals table
 