Feature: Reports Management

  Background:
    Given a user go to system administartion app
    
  @selenium
  @reports
  Scenario: Report Management
    Then the system loads system administrationpage
    And  user click on advanced administration page
   # Then user click on report administration link
