package com.ford.auto.POJO;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;

public class POJOExample {
	
	protected String newUserId;
	
	@Test(priority=1)
	public void createNewUser() {		
		NewUser newuser = new NewUser();
		newuser.setName("Stephen M D");
		newuser.setGender("Male");
		newuser.setEmail("testEmailSix@gorestApiSample6.com");
		newuser.setStatus("Active");
			
		String strResponse=given()
			.proxy("internet.ford.com", 83)
			.baseUri("https://gorest.co.in/public-api/users")
			/* Below "Bearer ACCESS-TOKEN" data is scrambled. 
			Please generate your own access-token from GOREST website "https://gorest.co.in/consumer/login" */
			.header("Authorization", "Bearer "+"<Generate your own token>")
			.contentType("application/json")			
			.body(newuser)		
		.when()
			.post()		
		.then()
			.log().all()
			.assertThat()
			.body("code",equalTo(201))			
			.extract().response().asString();
		
		JsonPath objJsonPath = new JsonPath(strResponse);
		newUserId = objJsonPath.getString("data.id");	
			
	}
	
	@Test(priority=2)
	public void ValidateNewUser() {		
		/* Building the GET method Endpoint with the "newUserId" variable that was updated 
		with the newly created record in above "createNewUser" Test */
		
		String apiEndPoint = "https://gorest.co.in/public-api/users/"+newUserId;
		System.out.println("API EndPoint is: "+apiEndPoint);
						
		GetUser getuser= given()
			.proxy("internet.ford.com", 83)
			.contentType("application/json")
			.baseUri(apiEndPoint)			
		.when()
			.get(apiEndPoint).as(GetUser.class);
		
		Assert.assertEquals(getuser.getCode(),200);
		Assert.assertEquals(getuser.getData().getName(),"Stephen M D");
		Assert.assertEquals(getuser.getData().getStatus(),"Active");
	}
	
	
}
