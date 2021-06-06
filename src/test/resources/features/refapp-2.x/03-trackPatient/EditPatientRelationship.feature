 Feature: EditRelationshipType
 Background:
    Given User clicks on Find Patient App
    
 @selenium
 @login
 Scenario: Searching an existing patient
       And  User clicks on first patient
       
 @selenium
 @login: 
 Scenario:System loads clinicianFacingPatientDashboardPage
    When User clicks on editRegistrationInformation
    And  User clicks on EditRelationships
    And  User click on find Relationships
    Then system loads back   to patientDashboard


