 Feature: EditRelationshipType
     
 @selenium
 @login: 
 Scenario:System loads clinicianFacingPatientDashboardPage
    When User clicks on editRegistrationInformation
    And  User clicks on EditRelationships
    And  User click on find Relationships
    Then system loads back   to patientDashboard


