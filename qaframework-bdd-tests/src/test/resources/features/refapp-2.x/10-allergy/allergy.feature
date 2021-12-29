Feature: Allery Management

  Background:
  Given a user clicks on Allergies link from the Patient dashboard
  Then the system loads the Allergy page

  @selenium
  @allergy
  Scenario: Allergy management
   When a user adds a known allergy into the system
   Then a user edits a known allergy
   And a user deletes a known allergy
