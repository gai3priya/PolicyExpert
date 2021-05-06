package com.ford.auto.RestAssuredSample;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import org.testng.annotations.Test;

public class RESTAssuredQueryPathParametersExample {
	
	   @Test
		public void TestCase_QueryParameters() {	
			given()
				 .proxy("internet.ford.com", 83)
				 .baseUri("https://reqres.in")	
				 .queryParam("page", 2).queryParam("id",9)
			.when()
			     .get("/api/users")
			.then()
				.statusCode(200)
				.log().all();				
			}
		
	   @Test
		public void TestCase_QueryParams_Example() {	
			given()
				 .proxy("internet.ford.com", 83)
				 .baseUri("https://reqres.in")
				 .queryParams("page", 2, "id", 9)				 
			.when()
		     	.get("/api/users")
		    .then()
				.statusCode(200)
				.log().all()	
				.assertThat()
				.body("data.id",equalTo(9))
				.log().all();			
			}
		
		@Test
		public void TestCase_QueryParams_UsingMapObject() {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("page", 2);
			map.put("id", 9);

			given()
				.proxy("internet.ford.com", 83)
				.baseUri("https://reqres.in")
				.queryParams(map)
			.when()
				.get("/api/users")
			.then()			
				.statusCode(200)
				.log().all()
				.assertThat()
				.body("data.id",equalTo(9));
						
		}
		
					
		@Test
		public void TestCaseOne_PathParameters() {	
			String id = "2";
			given()
				 .proxy("internet.ford.com", 83)
				 .baseUri("https://reqres.in/api/users")	
				 .pathParam("id",id)
			.when()
			     .get("/{id}")
			.then()
				.statusCode(200)
				.log().all();			
			}
		
		@Test
		public void TestCaseTwo_PathParameters() {	
			String id = "1";
			given()
				 .proxy("internet.ford.com", 83)
				 .baseUri("http://dummy.restapiexample.com/api")	
				 .pathParam("id",id)
			.when()
			     .get("/v1/employee/{id}")
			.then()
				.statusCode(200)
				.assertThat()				
				.body("status",equalTo("success"))
				.body("data.employee_name",equalTo("Tiger Nixon"))
				.log().all();			
			}
		
		@Test
		public void TestCase_MultiplePathParameters() {	
			String countryCode = "us";
			String zipCode="90210";
			
			given()
				 .proxy("internet.ford.com", 83)
				 .baseUri("http://api.zippopotam.us/")	
				 .pathParam("countryCode",countryCode)
				 .pathParam("zipCode", zipCode)
			.when()
			     .get("{countryCode}/{zipCode}")
			.then()
				.statusCode(200)
				.log().all();			
			}
		
}
