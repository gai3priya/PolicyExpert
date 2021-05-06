package com.ford.auto.RestAssuredSample;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestSpecificationExample {
	
	private static RequestSpecification requestSpec;
	private static ResponseSpecification responseSpec;
	
	@BeforeClass
	public static void createRequestResponseSpecification() {
		requestSpec = new RequestSpecBuilder()
							.setBaseUri("http://api.zippopotam.us")
							.setBasePath("us")
							.build();
		
		responseSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
	}	
	
	@Test
	public void getRequestTestCaseOne() {
		given().
			proxy("internet.ford.com", 83).
			spec(requestSpec).
		when().
			get("90210").
		then().
			spec(responseSpec);		
	}
	
	@Test
	public void getRequestTestCaseTwo() {
		given().
			proxy("internet.ford.com", 83).
			spec(requestSpec).
		when().
			get("90310").
		then().
		    spec(responseSpec);					
	}
	
	@Test
	public void getRequestTestCaseThree() {		
		RequestSpecification reqSpec = new RequestSpecBuilder()
				.setBaseUri("http://reqres.in")
				.setBasePath("api/users")
				.build();
		ResponseSpecification resSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
		
		given().
			proxy("internet.ford.com", 83).
			spec(reqSpec).
			queryParam("page","2"). //Additional request detail
		when().
			get().
		then().			
		    spec(resSpec).
		    body("total",equalTo(12)). //Additional response validations
			body("data.id[0]",equalTo(7)).
			body("data.first_name[0]",equalTo("Michael"));
	}

}
