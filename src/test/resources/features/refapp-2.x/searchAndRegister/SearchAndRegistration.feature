Feature: Search And Patient Registration

  @selenium
  @searchAndRegistration
  Scenario: Searching for missing patient
   Given   a user clicks on Find Patient Record App
    And    a user searches Olora Job
    Then   The System returns no patient

    #registering new patient
    And    The system loads homePage
    And    System clicks on registration app
    Then   User enters patient details for Olora Job
    And    system loads clinicianFacingPatientDashboard
    
