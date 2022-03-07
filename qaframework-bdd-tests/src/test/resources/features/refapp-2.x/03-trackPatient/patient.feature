Feature: Patient Record Management

  @selenium
  @patient
  Scenario Outline: Find Patient By Name
    Given a user creates a patient
    When a user clicks on Find Patient Record app from the home page
    And the system loads find patient page
    And a user enters the patient's "<name>"
    Then the patient search is "<status>"
    Examples:
    | name        | status       |
    | rightName   | successful   |
    | wrongName   | unsuccessful |

  @selenium
  @patient
  Scenario: Find Patient Record
    Given a user creates a patient
    When a user clicks on Find Patient Record app from the home page
    And the system loads find patient page
    And a user searches for an existing patient by name
    And a user clicks on returned patient
    Then the system loads the patient dashboard page

  @selenium
  @patient
  Scenario: Register Unidentified Patient
    Given a user clicks on Register a patient App from the home page
    When the system loads Register a patient page
    And a user clicks on the Unidentified Patient checkbox
    And a user selects a gender
    And a user clicks Confirm button
    Then the system adds patient record into the patients table

  @selenium
  @patient
  Scenario: Unidentified Patient Keyboard
    Given a user clicks on Register a patient App from the home page
    When the system loads Register a patient page
    And a user clicks on the Unidentified Patient checkbox
    And a user selects a gender
    And a user clicks Confirm button
    Then the system loads unidentified patient keyboard

  @selenium
  @patient
  Scenario: Duplicate Patient Register
    # User Story: Register first patient
    Given a user clicks on Register a patient App from the home page
    When the system loads Register a patient page
    And a user captures the patient details
    And a user clicks Confirm button
    Then the system displays patient dashboard page
    # User Story: Register second patient
    Given a user clicks on Register a patient App from the home page
    When the system loads Register a patient page
    And a user captures the patient details
    And the system alerts that similar patient found
    And a user clicks Confirm button
    Then the system displays patient dashboard page

  @selenium
  @patient
  Scenario: Name Patient Accented Letter
    Given a user clicks on Register a patient App from the home page
    When the system loads Register a patient page
    And a user captures patient name with accented letter
    And a user clicks Confirm button
    Then the system displays patient dashboard page

  @selenium
  @patient
  Scenario: Merge Patients
    Given a user creates two patients
    When a user clicks on Data Management App from the home page
    And the system loads data management page
    And a user clicks on Merge Patient Electronic Records app
    And the system loads merge patients page
    And a user captures IDs for patients to merge
    And a user clicks on Continue button
    And a user selects the preferred record
    And a user clicks on yes continue button
    Then the system loads patient visits dashboard page

  @selenium
  @patient
  Scenario: Using Back Button In Merge Patient
    Given a user creates two patients
    When a user clicks on Data Management App from the home page
    And the system loads data management page
    And a user clicks on Merge Patient Electronic Records app
    And the system loads merge patients page
    And a user captures IDs for patients to merge
    And a user clicks on Continue button
    And a user clicks on No button
    And the system returns to capture patient ID section
    And a user captures IDs for patients to merge
    Then the system displays Continue button
