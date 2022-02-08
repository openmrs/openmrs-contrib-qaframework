Feature: User based login

@selenium
@defaultLogin
Scenario Outline: User Based login verification
   When user verifies modules available on home page
   And user verifies modules available on homepage after login as clerk
   And user verifies modules available on home page after login as Docter
   And user verifies modules available on home page after login as Nurse
   And user verifies modules available on home page after login as sysadmin
   Then system goes back to login page