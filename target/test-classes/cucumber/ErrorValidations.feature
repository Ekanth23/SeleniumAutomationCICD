Feature: Error validation
@ErrorValidation
Scenario Outline:
Given I landed on the Ecommerce page
When  Logged in with username <name> and password <password>
Then "Incorrect email or password." message is displayed
Examples:
|name					|password		|
|ekanthrajan@gmail.com	|Password@	|





