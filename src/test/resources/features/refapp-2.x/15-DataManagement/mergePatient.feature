Feature: Data Management Test


    @selenium
    @dataManagement
    Scenario: Merge Patient using two patient identfiers
    Given User click data management from home Page
    And The system loads data management page
    Then User clicks on merge Patient elctronic records
    And User enter patient1 using patient identifier
    Then User enters patient2 using patient identifier
    And User clicks on  continue
    And User clicks on mergePatients"
    Then User clicks on continue from dashbaord
