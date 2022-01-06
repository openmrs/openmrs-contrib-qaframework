Feature: Vist management

@selenium
@visit
Scenario: Visit management
Given User click on first Patient
Then User clicks on start visit
And the system loads patient visits dashboardPage
And user goes back to the dashboardPage
# Then user clicks on past visit
# And system loads patient dashboard page
