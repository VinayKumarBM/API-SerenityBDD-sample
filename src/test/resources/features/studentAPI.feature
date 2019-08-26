Feature: To test the API of Student Application

@Smoke
Scenario: To verify that user is able to get the details of all the students in Student Application
		Given User has access to Student Application
		When User makes a GET call through endpoint '/list'
		Then User verifies that staus code is '200'
		
@Smoke
@SutudentPost
Scenario Outline: To create a new student in Student Application
		Given User has access to Student Application
		When User makes a POST call to create student API endpoint URL
		|firstName		|lastName	|email		|programme			|courses		|
		|<first_Name>	|<last_Name>|<email_id>	|<programme_name>	|<courses_name>	|
		Then User verifies that staus code is '201'
		And User verifies the response has success message <msg>
Examples:
		|first_Name	|last_Name	|email_id			|programme_name	|courses_name							|msg			|
		|Jon		|Doe		|Jon.Doe@gmail.com	|Annonymus		|New course,Another course				|Student added	|
		|Jane		|Doe		|Jane.Doe@gmail.com	|Annonymus		|Being Annonymus, How to be Annonymus	|Student added	|