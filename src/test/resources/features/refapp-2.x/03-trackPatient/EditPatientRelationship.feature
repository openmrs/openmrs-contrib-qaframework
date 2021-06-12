 Feature: EditRelationshipType


   @editPatientRelationship
   @login:
   Scenario:User clicks on find Patient record
     And System loads clinicianFacingPatientDashboardPage
     Then User clicks on RegistrationSummary
     And  User click on editPatientRelationshipPage
     And  User clicks   on  SelectRelationshipType
     Then system loads back to patientDashboard


