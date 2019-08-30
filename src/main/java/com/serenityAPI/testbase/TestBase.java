package com.serenityAPI.testbase;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import com.serenityAPI.utils.StudentApplication;
import io.restassured.RestAssured;

public class TestBase {

	@BeforeClass
	public static void init() throws IOException, InterruptedException {
		StudentApplication application = new StudentApplication();
		application.start();
		RestAssured.baseURI = "http://localhost:8888/student";
	}

	@AfterClass
	public static void teardown() throws IOException, InterruptedException {
		StudentApplication application = new StudentApplication();
		application.shutdown();
	}
}
