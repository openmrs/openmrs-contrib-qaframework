Feature: Form Management

  Background:
  Given  a user click on manage html forms

  @selenium
  @forms
  Scenario: Form management
   When check the availability of forms
   Then a user adds a form in the system
   And  a user edits a form
   Then a user deletes a form

