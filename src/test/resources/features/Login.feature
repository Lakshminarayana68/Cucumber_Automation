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