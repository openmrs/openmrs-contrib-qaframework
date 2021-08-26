Feature: EditRelationshipType
   
   @selenium
   @editPatientRelationship
   Scenario: Edit patient relationship
     Given System clicks on registration app
     And  User enters patient details for Origin Ashaba
     Then User clicks on RegistrationSummary
     And  User click on editPatientRelationshipPage
     And  User clicks on selectRelationshipType
     Then system loads back to patientDashboard
