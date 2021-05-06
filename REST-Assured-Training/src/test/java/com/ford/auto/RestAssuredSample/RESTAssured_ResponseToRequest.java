package com.ford.auto.RestAssuredSample;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;

import io.restassured.path.json.JsonPath;
import io.restassured.response.*;

public class RESTAssured_ResponseToRequest {
	
	protected static String userId;
	protected static String userName;
	protected static String userEmailId;
	
	@Test(priority=1)
	public static void getUser() {
		System.out.println("**************** GET User Test Case********************");
		String strResponse=given()
								.proxy("internet.ford.com",83)
								.baseUri("https://gorest.co.in/public-api/users")
								/* Please generate your own acces-token from GOREST website "https://gorest.co.in/consumer/login" */
								.header("Authorization","Bearer <Enter your access token here>")								
								.contentType("application/json")
				.when()
					.get()
			.then()
					.log().all()
					.assertThat()
					.body("code",equalTo(200))
					.extract().response().asString();
		
		JsonPath objJsonPath = new JsonPath(strResponse);
		userId=objJsonPath.getString("data.id[0]");
		userName=objJsonPath.getString("data.name[0]");
		userEmailId=objJsonPath.getString("data.email[0]");
		System.out.println("**** Details of Extracted User *******");
		System.out.println("User id: "+userId);
		System.out.println("User Name: "+userName);
		System.out.println("User EmailId: "+userEmailId);		
	}
	
	@Test(priority=2)
	public static void updateUser() {
		System.out.println("**************** UPDATE User Test Case********************");
		JSONObject request = new JSONObject();
		request.put("name","John M A");
		request.put("email","John_MA_UpdatedEmail@TestAPISample2.com");
		request.put("gender","Male");
		request.put("status","Active");		
		
		given()
			.proxy("internet.ford.com",83)
			.baseUri("https://gorest.co.in/public-api/")
			/* Please generate your own acces-token from GOREST website "https://gorest.co.in/consumer/login" */
								.header("Authorization","Bearer <Enter your access token here>")								
								.contentType("application/json")
								.body(request.toJSONString())
		.when()
			.put("users/"+userId)
		.then()
			.log().all()
			.assertThat()
			.body("code",equalTo(200))
			.body("data.name",equalTo("John M A"))
			.body("data.email",equalTo("John_MA_UpdatedEmail@TestAPISample2.com"));
	}
	

}
