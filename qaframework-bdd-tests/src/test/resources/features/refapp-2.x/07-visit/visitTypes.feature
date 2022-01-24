Feature: Visit types Management

  Background:
    Given a user clicks on the system administration link from the home page
    Then the administration page gets loaded
    And a user clicks on the manage Visit Types link
    Then the manage visit types page is loaded

  @selenium
  @visitType
  Scenario: Adding a Visit Type
    When a user clicks on the add visit type link on the manage visit type page
    Then the system loads the visit type form page
    And a user fills the add visit type form
    And a user saves visit type
    Then a visit type is saved successfully

  @selenium
  @visitType
  Scenario: Editing a Visit Type Test
    When a user clicks on the already saved visit type link
    Then the system loads the visit type form page
    And a user edits the visit type name
    Then a visit type is saved successfully

  @selenium
  @visitType
  Scenario: Retiring a Visit Type Test
    When a user clicks on the already saved visit type link
    Then the system loads the visit type form page
    And a user fills in the reason for retiring visit type
    Then a user clicks on retire visit type button

  @selenium
  @visitType
  Scenario: Deleting a Visit Type Test
    When a user clicks on the already saved visit type link
    Then the system loads the visit type form page
    And a user clicks on delete visit type button
    Then a visit type is deleted successfully
