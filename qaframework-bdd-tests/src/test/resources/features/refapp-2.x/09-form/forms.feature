Feature: Form Management

  Background:
  Given  a user click on manage html forms
  Then the system loads manage html forms page

  @selenium
  @form
  Scenario: Form management
   When a user check the availability of forms
   Then a user adds a form in the system
   And user clicks on edit form
   Then user clicks on delete form
