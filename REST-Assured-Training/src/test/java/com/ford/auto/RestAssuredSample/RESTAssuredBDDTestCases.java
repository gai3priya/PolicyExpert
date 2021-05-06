package com.ford.auto.RestAssuredSample;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;


public class RESTAssuredBDDTestCases {
	
	@Test
	public void TestCase_GET_Request() {
		given()
		 .proxy("internet.ford.com", 83)
		 .baseUri("https://restcountries.eu/rest/v2/")		 
	.when()
	     .get("capital/madrid")
	.then()
		.statusCode(200)
		.body(containsString("Europe"))		
		.log().all();	
		
	}
	
	@Test
	public void TestCase_POST_Request() {
		String jsonString = "{\"name\" : \"morpheus\",\"job\" : \"TeamLeader\"}";		
		given()
			.proxy("internet.ford.com", 83)
			.baseUri("https://reqres.in/api/users")
			.contentType(ContentType.JSON)
			.body(jsonString)		
		.when()
			.post()		
		.then()
			.assertThat()
			.statusCode(201)			
			.body("name",equalTo("morpheus"))
			.body("job",equalTo("TeamLeader"))
			.log().all();	
	}
	
	@Test
	public void TestCase_PUT_Request() {
		JSONObject request = new JSONObject();
		request.put("name", "John M");
		request.put("job", "System Analyst");
		
		given()
			.proxy("internet.ford.com", 83)
			.body(request.toJSONString())
		.when()
			.put("https://reqres.in/api/users/2")
		.then()
			.statusCode(200)
			.statusLine("HTTP/1.1 200 OK")	
			.contentType(ContentType.JSON)
			.log().all();
	}
	
	@Test
	public void TestCase_DELETE_Request() {	
		given()
			 .proxy("internet.ford.com", 83)
			 .baseUri("https://reqres.in")			 
		.when()
		     .delete("/api/users/2")
		.then()
			 .log().all()
		     .assertThat()
			 .statusCode(204)
			 .and()
			 .header("server","cloudflare");	
		}
	
	/* Additional Test cases */
	@Test
	public void SampleTestCaseOneForPOSTUsingHashMap(){
		Map<String, String> map = new HashMap<String, String>();
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
		RestAssured.basePath = "/posts/";
		map.put("userId", "2");
		map.put("id", "19");
		map.put("title", "this is projectdebug.com");
		map.put("body", "this is REST-Assured Tutorial");		
		given()
			.proxy("internet.ford.com", 83)
			.contentType("application/json")
			.body(map)
		.when()
			.post()
		.then()
			.statusCode(201)
			.and()
			.body("title", equalTo("this is projectdebug.com"))
			.and()
			.log().all();
	}
	
	@Test
	public void SampleTestCaseTwoForPOSTUsingHashMap() {		
		HashMap<String,Object> requestDetails = new HashMap<String,Object>();
		requestDetails.put("name", "Steve Smith");
		requestDetails.put("job", "Project Manager");
		JSONObject request = new JSONObject(requestDetails);
		given().
			proxy("internet.ford.com", 83).
			body(request.toJSONString()).
		when().
			post("https://reqres.in/api/users").
		then().
			statusCode(201);
			
	}
	

	

}
