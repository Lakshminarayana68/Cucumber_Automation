Feature: Create account

@signup
Scenario: validate user creation
Given user launches the browser
When user navigates to Home page
When user clicks on "signup_login" on "home" page
Then user wait for 5 seconds
Then user navigated to "LoginPage" page
When user enters "user" in "username" field
When user enters "emailname" in "email" field
When user click on "signup" button
Then user wait for 5 seconds
Then user navigated to "SignUp" page
When user click on "Male"
When user enters "password" in "password" field
#When user select "6" from "day" dropdown
When user select "March" from "month" dropdown
When user select "1997" from "year" dropdown
When user click on "newsletter"
When user enters "first_name" in "first_name" field
When user enters "last_name" in "last_name" field
When user enters "address" in "address" field
When user enters "state" in "state" field
When user enters "zipcode" in "zipcode" field
When user enters "state" in "state" field
When user enters "city" in "city" field
When user enters "mobile_number" in "mobile_number" field
When user click on "create_account" button

