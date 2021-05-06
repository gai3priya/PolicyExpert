package com.ford.auto.RestAssuredSample;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
import io.restassured.http.Cookie;

public class RESTAssured_CookieExample {
	
	/* Below three TestNG methods are for informational purpose only. They wont execute */
	
	@Test
	public void cookieMethodExample() {
		given()
			.baseUri("http://localhost")
			.cookie("session_id","1234")
		.when()
			.get("users/TestUser1")
		.then()
			.statusCode(200);	
	}

	@Test
	public void cookiesMethodExample() {
		given()
			.baseUri("http://localhost")
			.cookies("session_id","1234","cookieName","cookieValue")
		.when()
			.get("users/TestUser1")
		.then()
			.statusCode(200);	
	}

	@Test
	public void cookieBuilderMethodExample() {		
		Cookie myCookie = new Cookie.Builder("session_id", "1234")
			      .setSecured(true)
			      .setComment("This is a sessionid cookie")
			      .setMaxAge(1000)
			      .setVersion(1)
			      .build();

			    given()
			    	.baseUri("http://localhost")
			    	.cookie(myCookie)
			    .when()
			    	.get("/users/TestUser1")
			    .then()
			    	.statusCode(200);
	}
}
