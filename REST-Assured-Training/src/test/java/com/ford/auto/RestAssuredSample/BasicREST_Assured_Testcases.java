package com.ford.auto.RestAssuredSample;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.*;


public class BasicREST_Assured_Testcases {	
		
	@Test
	public void TestCaseforGETMethod()
	{	
		RestAssured.proxy("internet.ford.com", 83);
		Response response = RestAssured.get("http://zippopotam.us/us/90210");		
		ValidatableResponse valRes = response.then();
		valRes.statusCode(200); 
		valRes.contentType("application/json");				
		System.out.println("*** Printing Response Details ***");
		System.out.println(response.getStatusCode());		
		System.out.println(response.getBody().asString());		
		System.out.println(response.getStatusLine());
		System.out.println(response.getHeader("content-type"));
		System.out.println(response.getTime());
	}
	
	@Test
	public void TestCaseTwoforGETMethod()
	{	
		given()
			.proxy("internet.ford.com", 83).
			get("http://zippopotam.us/us/90210")		
		.then()
			.statusCode(200) 
		    .contentType("application/json");
	}	
}
