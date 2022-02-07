Feature: Reports Management

  Background:
    Given a user go to system administartion app
    Then the system loads system administrationpage

  @selenium
  @report
  Scenario: Report Management
    When user clicks on advanced administration page
    And user clicks on report administration link
    Then the system loads manage reports page
    And user clicks on run button from manage Reports page
    And user enters start date
    And user enters end date
    Then user clicks on view report link
