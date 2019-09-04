package com.serenityAPI.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serenityAPI.steps.StudentAPISteps;
import com.serenityAPI.utils.RandomDataUtil;

import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;

@FixMethodOrder (MethodSorters.NAME_ASCENDING)
@RunWith (SerenityRunner.class)
public class APISerenityTests {
	private final Logger log = LoggerFactory.getLogger(APISerenityTests.class);
	RandomDataUtil dataUtil = new RandomDataUtil();
	private String firstName = dataUtil.generateRandomData();
	private String lastName = dataUtil.generateRandomData();
	private String email = firstName+"."+lastName+"_"+System.currentTimeMillis()+"@gmail.com";
	private String programme = "Student Creation Program";
	private List<String> courses = new ArrayList<String>();
	private static int studentID;
	
	private Response response; 

	@Steps
	StudentAPISteps studentAPISteps;

	@WithTags(
			{
				@WithTag("PositiveTest"),
				@WithTag("SmokeTest"),
				@WithTag("GetTest")
			}
			)
	@Title ("Test to get list of all the Students")
	@Test
	public void test001_GetAllStudentDetails() {
		response = studentAPISteps.getStudentInfo("/list");
		studentAPISteps.validateResonseCode(response, 200);
	}

	@WithTags(
			{
				@WithTag("SmokeTest"),
				@WithTag("PostTest")
			}
			)
	@Title ("Test to create a Student and verify that Student is created successfully")
	@Test 
	public void test002_CreateStudent() {
		courses.add("Mathematics");
		courses.add("Physics");
		courses.add("Chemistery");
		
		response = studentAPISteps.createStudent(firstName, lastName, email, programme, courses);
		studentAPISteps.validateResonseCode(response, 201);		
		studentAPISteps.validateResonseMessage(response, "msg", "Student added");

		HashMap<String, Object> responseMap = studentAPISteps.getStudentInfoByFirstName(firstName);
		log.info("Newly Created Student:\n"+responseMap);
		studentID = (int) responseMap.get("id");
		log.info("Student ID: "+studentID);
	}

	@WithTags(
			{
				@WithTag("SmokeTest"),
				@WithTag("PutTest")
			}
			)
	@Title ("Test to update a Student and verify that Student is updated successfully")
	@Test 
	public void test003_UpdateStudent() {
		courses.add("Mathematics");
		courses.add("Physics");
		courses.add("Chemistery");

		lastName = lastName+"_Updated";
		programme = "Updated Programme";

		response = studentAPISteps.updateStudent(firstName, lastName, email, programme, courses, studentID);
		studentAPISteps.validateResonseCode(response, 200);
		studentAPISteps.validateResonseMessage(response, "msg", "Student Updated");
		
		HashMap<String, Object> responseMap = studentAPISteps.getStudentInfoByFirstName(firstName);
		log.info("Updated Student:\n"+responseMap);
		assertEquals("Last Name did not match",lastName, responseMap.get("lastName"));
		assertEquals("Progamme did not match",programme, responseMap.get("programme"));
	}

	@WithTags(
			{
				@WithTag("SmokeTest"),
				@WithTag("DeleteTest")
			}
			)
	@Title ("Test to delete Student details")
	@Test
	public void test004_DeleteStudentDetails() {
		response = studentAPISteps.deleteStudentInfo(studentID);
		studentAPISteps.validateResonseCode(response, 204);
	}
	
	@WithTag("NegativeTest")
	@Title ("Test to verify the status code when API call is made with invalid method type")
	@Test
	public void test005_GetStudentDetailsWithInvalidMethodCall() {
		SerenityRest.rest()
		.given()
		.when().post("/list")
		.then().assertThat().statusCode(405)
		.and().log().all();
	}
	
	@WithTag("NegativeTest")
	@Title ("Test to verify the status code when API call is made with invalid Endpoint URL")
	@Test
	public void test006_GetStudentDetailsWithInvalidEndpoint() {
		response = studentAPISteps.getStudentInfo("/list1");
		studentAPISteps.validateResonseCode(response, 400);
	}
}
