package com.serenityAPI.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ReuseableSpecifications {

	private static RequestSpecBuilder requestSpecBuilder;
	private static RequestSpecification requestSpec;
	
	private static ResponseSpecification responseSpec;
	private static ResponseSpecBuilder responseSpecBuilder;

	public static RequestSpecification getGenericRequestSpec() {
		requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder.setContentType(ContentType.JSON);
		requestSpec = requestSpecBuilder.build();
		return requestSpec;
	}
	
	public static ResponseSpecification getGenericResponseSpec() {
		responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectHeader("Content-Type", "application/json;charset=UTF-8");
		responseSpecBuilder.expectHeader("Transfer-Encoding", "chunked");
		responseSpec = responseSpecBuilder.build();
		return responseSpec;
	}
}
