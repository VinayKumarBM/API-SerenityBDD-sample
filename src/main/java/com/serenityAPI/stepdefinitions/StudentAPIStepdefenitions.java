package com.serenityAPI.stepdefinitions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.serenityAPI.steps.StudentAPISteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

public class StudentAPIStepdefenitions {
	private final String studentAppURL = "http://localhost:8888/student";
	private Response response;

	@Steps
	private StudentAPISteps studentAPISteps;
	
	@Given("^User has access to Student Application$")
	public void user_has_access_to_Student_Application() {
		RestAssured.baseURI = studentAppURL;
	}


	@When("^User makes a GET call through endpoint '(.*)'$")
	public void user_makes_a_GET_call_through_endpoint(String endpointURL) {
		response = studentAPISteps.getStudentInfo(endpointURL);
		Serenity.setSessionVariable("response").to(response);
	}

	@Then("^User verifies that staus code is '(\\d+)'$")
	public void user_verifies_that_staus_code_is(int statusCode) {
		studentAPISteps.validateResonseCode(Serenity.sessionVariableCalled("response"), statusCode);
	}

	@When("^User makes a POST call to create student API endpoint URL$")
	public void user_makes_a_POST_call_to_create_student_API_endpoint_URL(DataTable dataTable) {
		List<Map<String,String>> studentDataMapList = dataTable.asMaps(String.class, String.class);
		List<String> courseList = Arrays.asList(studentDataMapList.get(0).get("courses").split(","));
		String emailArray[] = studentDataMapList.get(0).get("email").split("@");
		String email = emailArray[0]+"_"+System.currentTimeMillis()+"@"+emailArray[1];
		response = studentAPISteps.createStudent(studentDataMapList.get(0).get("firstName"), studentDataMapList.get(0).get("lastName"), 
				email, studentDataMapList.get(0).get("programme"), courseList);
		Serenity.setSessionVariable("response").to(response);
	}
	
	@Then("^User verifies the response has success message (.*)$")
	public void user_verifies_the_response_has_success_message(String expectedMessage) {
		studentAPISteps.validateResonseMessage(Serenity.sessionVariableCalled("response"), "msg", expectedMessage);
	}
		
}
