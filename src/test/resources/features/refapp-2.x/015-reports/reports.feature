Feature: Reports Management

  Background:
    Given a user go to system administartion app
    
  @selenium
  @report
  Scenario: Report Management
    Then  the system loads system administrationpage
    When  user click on advanced administration page
    And   user click on report administration link
    Then  the system loads manage reports page
    And   user clicks on run button from manage Reports page
    And   user enter start date
    Then  user enter end date
    And   user clicks on request report
    And   user clicks on view report link
    