Feature: Loading styles guide

  @selenium
  @login
  Scenario: Loading the styles page
    Given user navigates to the systems admin page
    And user presses the styles guide link
    Then system should load the styles guide page
    When user clicks back
    Then system should return to the previous page