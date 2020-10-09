Feature: Adding attachments

  Background: 
    Given User logins in with right credentials
    And serches patients,  clicks on first patient and visits patient dashboard
    When patient visit is started

  Scenario: Launching attachment page
    And user clicks on attachemnts function
    Then System launches attachments page

  Scenario: Uploading attachment
    Given system launches attachments page
    And user clicks in dropzone  or drops a file in the drop zone
    Then propt to select the attachemnt is ivoked
    And user selects attachemnts
    And user clicks open
    Then attachment is loaded into the form
    And user adds cation
    And clicks upload file
    Then then the file is uploaded

Scenario: Clear form
Given attachment is loaded into the form
And user clicks clear form button 
Then the loaded attacments are cleared from the form 
