package com.serenityAPI.steps;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.serenityAPI.model.Student;
import com.serenityAPI.utils.ReuseableSpecifications;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StudentAPISteps {
	private final Logger log = LoggerFactory.getLogger(StudentAPISteps.class);
	
	@Step ("Create a Student with firstName: {0}, lastName: {1}, email: {2}, programme: {3}, courses: {4}")
	public Response createStudent(String firstName, String lastName, String email, String programme, 
			List<String> courses ) {
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);

		return SerenityRest.rest()
				.given().spec(ReuseableSpecifications.getGenericRequestSpec())
				.and().body(student)
				.and().log().all()
				.when().post();
	}

	@Step ("Validate that expected status code is {1}")
	public void validateResonseCode(Response response, int expectedStatusCode) {
		assertEquals("Response code did not match ",expectedStatusCode, response.getStatusCode());
	}
	
	@Step ("Validate that expected status code is {1}")
	public void validateResonseMessage(Response response, String jsonPath, String expectedMessage) {
		response.then().log().all();
		String msg = response.jsonPath().get(jsonPath);		
		log.info("Message: "+msg);
		assertEquals("Response message did not match ", expectedMessage, msg);
	}
	
	@Step ("Get the Student information based on firstName: {0}")
	public HashMap<String, Object> getStudentInfoByFirstName(String firstName) {
		return SerenityRest.rest()
				.given()
				.when().get("/list")
				.then().assertThat().statusCode(200)
				.and().log().all()
				.and().extract().path("findAll{it.firstName=='"+firstName+"'}.get(0)");
	}

	@Step ("Update a Student details with firstName: {0}, lastName: {1}, email: {2}, programme: {3}, courses: {4} for a Student with ID: {5}")
	public Response updateStudent(String firstName, String lastName, String email, String programme, 
			List<String> courses, int studentID) {
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);

		return 	SerenityRest.rest()
				.given().spec(ReuseableSpecifications.getGenericRequestSpec())
				.and().body(student)
				.and().log().all()
				.when().put("/"+studentID);
	}
	
	@Step ("Delete the Student information with ID: {0}")
	public Response deleteStudentInfo(int studentID) {
		return SerenityRest.rest()
				.given().log().all()
				.when().delete("/"+studentID);
	}
	
	@Step ("Get all the Student details")
	public Response getStudentInfo(String endpointURL) {
		return SerenityRest.rest()
				.given()
				.when().get(endpointURL);
	}
}
