package com.ford.auto.RestAssuredSample;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ValidateJSONResponse {	
	
	@Test
	public void testCase_ValidateJSONResponse() {		
		given()
		.proxy("internet.ford.com", 83)
		.baseUri("https://jsonplaceholder.typicode.com/")		
	.when()
		.get("users/1")		
	.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")		
		.body("id", equalTo(1))		
		.body("name",equalTo("Leanne Graham"))
		.body("address.street",equalTo("Kulas Light"))
		.body("address.geo.lat",equalTo("-37.3159"))
		.body("company.bs",equalTo("harness real-time e-markets"));
	}
	
	
	@Test
	public void testCaseForExtractMethod() {
	String id = 
	given()
		.proxy("internet.ford.com", 83)
		.baseUri("https://chercher.tech/sample/api/")		
	.when()
		.get("product/read")		
	.then()		
		.extract().path("records[0].id");		
	Assert.assertEquals(id, "4202");
	
	/*ID value extracted from above request is passed as
	input to the next request */
	given()
		.baseUri("https://chercher.tech/sample/api/")		
	.when()
		.get("product/read?="+id)		
	.then()
		.statusCode(200);	
	}
	
	
	
	@Test
	public void testCaseForResponseJSONPath() {
		String strResponse = 
			given()
				.proxy("internet.ford.com", 83)
				.baseUri("https://chercher.tech/sample/api/")		
			.when()
				.get("product/read")
			.then()
				.extract().response().asString();
		
		JsonPath objJsonPath = new JsonPath(strResponse);
		String arrFirstRecordName = objJsonPath.getString("records[0].name");
		System.out.println(arrFirstRecordName);
		
		int size = objJsonPath.getInt("records.size()");
		System.out.println("Size of response is: " +size);
	}
	
}