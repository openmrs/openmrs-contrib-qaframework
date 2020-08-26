Feature: loading styles guide

  Scenario: Loading the styles page
    Given a user logins into the system
    And user navigates to the systems admin page
    When user presses the styles guide link
    Then system should load the styles guide page
    And user clicks back
    Then system should return to the previous page
    And Close styles browser instance