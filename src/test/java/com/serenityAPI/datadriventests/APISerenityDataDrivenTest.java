package com.serenityAPI.datadriventests;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.serenityAPI.steps.StudentAPISteps;
import com.serenityAPI.testbase.TestBase;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;

@Concurrent (threads = "4x")
@UseTestDataFrom ("testdata/StudentInfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class APISerenityDataDrivenTest extends TestBase{
	private Logger log = LoggerFactory.getLogger(APISerenityDataDrivenTest.class);
	@Steps
	StudentAPISteps studentAPISteps;
	
	private String firstName;
	private String lastName;
	private String email;
	private String programme;
	private String courses;
	
	private List<String> coursesList = new ArrayList<String>();
	private int studentID;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProgramme() {
		return programme;
	}
	public void setProgramme(String programme) {
		this.programme = programme;
	}
	public String getCourses() {
		return courses;
	}
	public void setCourses(String courses) {
		this.courses = courses;
	}
	
	@WithTag("PostTest")
	@Title ("Test to create a Student with data from CSV file and verify that Student is created successfully")
	@Test
	public void createStudent() {
		log.info(courses);
		coursesList.add(courses);
		
		Response response = studentAPISteps.createStudent(firstName, lastName, email, programme, coursesList);
		studentAPISteps.validateResonseCode(response, 201);		
		String msg = response.jsonPath().get("msg");		
		log.info("Message: "+msg);
		assertEquals("Student was not created successfully", "Student added", msg);

		HashMap<String, Object> responseMap = studentAPISteps.getStudentInfoByFirstName(firstName);
		log.info("Newly Created Student:\n"+responseMap);
		studentID = (int) responseMap.get("id");
		log.info("Student ID: "+studentID);
	}
}
