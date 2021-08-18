Feature: Search And Patient Registration

  @selenium
  @searchAndRegistration
  Scenario: Searching for missing patient
    Given   a user clicks on Find Patient Record App
    And     a user searches origin ashaba
    Then    The System returns no patient

    #registering new patient
    And    The system loads homePage
    And    System clicks on registration app
    Then   User enters patient details for Origin Ashaba
    And    system loads clinicianFacingPatientDashboard

    #delete the same patient
    And  user click on delete patient

