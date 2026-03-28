@tag
Feature: Purchase the order from Ecommerce Website

Background:
Given I landed on the Ecommerce page

Scenario Outline:
Positive Test of Submitting the order 
Given Logged in with username <name> and password <password>
When I add product <productName> from cart
And Checkout <productName> and submit the order
Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation page 

Examples:
|name					|password		|productName|
|ekanthrajan@gmail.com	|Password@123	|ZARA COAT 3|