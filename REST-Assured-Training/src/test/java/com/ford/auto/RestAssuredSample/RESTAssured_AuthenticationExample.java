package com.ford.auto.RestAssuredSample;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class RESTAssured_AuthenticationExample {
	
	@Test
	public void basicAuthenticationExample() {
		given()
			.proxy("internet.ford.com",83)
			.baseUri("https://the-internet.herokuapp.com/login")
			.auth()
				.basic("tomsmith", "SuperSecretPassword!")
		.when()
			.get()
		.then()
			.statusCode(200);
	}
	
	@Test
	public void preemptiveBasicAuthenticationExample() {
		given()
			.proxy("internet.ford.com",83)
			.baseUri("https://the-internet.herokuapp.com/login")
			.auth()
				.preemptive()
				.basic("tomsmith", "SuperSecretPassword!")
		.when()
			.get()
		.then()
			.statusCode(200);
	}
	
	/* Below test is for information purpose, this will not execute */
	@Test
	public void OAuth2AuthenticationExample() {
		given()
			.proxy("internet.ford.com",83)
			.baseUri("https://localhost/getUserList")
			.auth()
				.preemptive()
				.oauth2("<Enter the access token here>")
		.when()
			.get()
		.then()
			.statusCode(200);
	}
	
	/* Below test is for information purpose, this will not execute */
	@Test
	public void OAuth2BearerTokenAuthenticationExample() {
		given()
			.proxy("internet.ford.com",83)
			.baseUri("https://localhost/getUserList")
			//Please generate your own access token from GORest Website https://gorest.co.in/consumer/login
			.header("Authorization","Bearer <Enter your access token here>")
			.contentType("application/json")
		.when()
			.get()
		.then()
			.log().all()
			.assertThat()
			.body("code",equalTo(200));
	}

}
