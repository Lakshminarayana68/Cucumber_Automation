Feature: Home page validation


@HomePage
Scenario: verify home page details

Given user launches the browser
When user navigates to Home page
Then user verify Headers
   |Home|
   |Products|
   |Cart|
   |Signup / Login|
   |Test Cases|
   |API Testing|
   |Video Tutorials|
   |Contact us|
   
@Login
Scenario Outline: Login verification

Given user launches the browser
When user navigates to Home page
When user clicks on "signup_login" on "home" page
Then user navigated to login page
When user enter "<username>" and "<password>"
When user clicks on login
Then user navigated to Automation Homepage

Examples:
|username|password|
|ram@12.com|ram@12|

@category @Home
Scenario Outline: verify categories

Given user launches the browser
When user navigates to Home page
When user clicks on "signup_login" on "home" page
Then user navigated to login page
When user enter "<username>" and "<password>"
When user clicks on login
Then user navigated to Automation Homepage
Then user verify categories
|WOMEN|
|MEN|
|KIDS|

Examples:
|username|password|
|ram@12.com|ram@12|

@brands @Home
Scenario Outline: verify categories

Given user launches the browser
When user navigates to Home page
When user clicks on "signup_login" on "home" page
Then user navigated to login page
When user enter "<username>" and "<password>"
When user clicks on login
Then user navigated to Automation Homepage
Then user verify brands
|POLO|
|H&M|
|MADAME|
|MAST & HARBOUR|
|BABYHUG|
|ALLEN SOLLY JUNIOR|
|KOOKIE KIDS|
|BIBA|

Examples:
|username|password|
|ram@12.com|ram@12|



@addtoCart
Scenario Outline:  Add product to cart

Given user launches the browser
When user navigates to Home page
When user clicks on "signup_login" on "home" page
Then user navigated to login page
When user enter "<username>" and "<password>"
When user clicks on login
Then user navigated to Automation Homepage
When user click on add to cart


Examples:
|username|password|
|ram@12.com|ram@12|



   
