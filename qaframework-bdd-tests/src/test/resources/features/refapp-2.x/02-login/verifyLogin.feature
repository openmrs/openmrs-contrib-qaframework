Feature: User Based Login

@selenium
@defaultLogin
Scenario: User Based login verification
   And user verifies modules available on home page after login as super user
   And user verifies modules available on homepage after login as clerk
   And user verifies modules available on home page after login as doctor
   And user verifies modules available on home page after login as nurse
   And user verifies modules available on home page after login as sysadmin
   Then check modules loaded into the system
   Then system goes back to login page
