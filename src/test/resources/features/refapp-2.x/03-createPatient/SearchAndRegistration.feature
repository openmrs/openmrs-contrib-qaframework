Feature: Search And Patient Registration
   Background:
   Given :User clicks on Find Patient Record App

  @selenium
  @searchAndRegistration
  Scenario: Searching for missing  patient called faiza
    And    User searches Andria Faiza
    Then  The System returns no patient
    
  @selenium
  @searchAndRegistration
  Scenario: Registering new patient
     And  The system loads homePage
     When User clicks on registrationapp
     And  User enters patient details
