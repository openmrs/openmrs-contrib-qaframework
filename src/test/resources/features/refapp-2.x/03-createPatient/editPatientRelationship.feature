Feature: EditRelationshipType
   
   @selenium
   @editPatientRelationship
   Scenario: Edit patient relationship
     Given User clicks on find Patient record 
     And  User enters John Taylor
     And  system returns a patients
     Then User clicks on first patient
     And  System loads patient dashboardPage
     Then User clicks on RegistrationSummary
     And  User click on editPatientRelationshipPage
     And  User clicks   on  SelectRelationshipType
     Then system loads back to patientDashboard
