Feature: Login Functionality Data Driven


  @login
  Scenario Outline: Verify login with multiple credentials
    Given User is on login page
    When User enters username "<username>" and password "<password>"
    And User clicks login button
    Then User should see homepage

    Examples:
      | username | password |
      | user1    | pass1    |
      | user2    | pass2    |

  @login_new
  Scenario: Verify Signup page
  Given user launches the browser
  Then user wait for 5 seconds
  When user navigates to Home page
  When user clicks on "signup_login" on "home" page
  Then user wait for 5 seconds
  Then user navigated to "LoginPage" page
  When user verify "signup text" text
  When user enters "username" in "username"
  When user enters "email" in "email"
  
   

