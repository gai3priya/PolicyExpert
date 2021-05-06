package com.ford.auto.RestAssuredSample;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import java.io.File;
import static io.restassured.RestAssured.*;

public class RESTAssuredDataDriven {
	
		@DataProvider
	    public static Object[][] userTestData() {
	        return new Object[][] {
	        	//Object of PageNumber ,ID, FirstName and LastName
	        	{2,5,"Charles","Morris"},
	        	{2,9,"Tobias","Funke"},
	        	{2,10,"Byron","Fields"},
	        	{2,11,"George","Edwards"},
	        	{2,12,"Rachel","Howell"}
	        };
	    }
	
	    @Test(dataProvider="userTestData")
	    public void TestCase_with_QueryParameter_DataDriven(int pageNumber, int id, String firstName, String lastName) 
	    {
	    //Example: Target API URL https://reqres.in/api/users?page=2&id=12 	
		 given()
		     .proxy("internet.ford.com", 83)
			 .baseUri("https://reqres.in")	
			 .queryParam("page", pageNumber).queryParam("id",id)
		 .when()
		     .get("/api/users")
		 .then()
			.statusCode(200)
			.body("data.first_name",equalTo(firstName))
			.body("data.last_name",equalTo(lastName))
			.log().all();
	    }
	
	
	
	
	/* #################################################################################################### */
	@DataProvider
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][] {
            { "us", "90210", "Beverly Hills" },
            { "us", "12345", "Schenectady" },
            { "ca", "B2R", "Waverley"}
        };
    }
	
	  @Test(dataProvider="zipCodesAndPlaces")
	    public void TestCase_withPathParameter_DataDriven(String countryCode, 
	    		String zipCode, String expectedPlaceName) 
	    {
	        given().
	        	proxy("internet.ford.com", 83).
	            pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
	        when().
	            get("http://zippopotam.us/{countryCode}/{zipCode}").
	        then().
	            assertThat().
	            body("places[0].'place name'", equalTo(expectedPlaceName));
	    }
	   
	  /* #################################################################################################### */
	   //JSON File as Payload
	   @Test
		public void passFileAsPayload() {		
		   File file = new File(System.getProperty("user.dir")+"\\src\\test\\java\\testDataJSON\\PayloadOne.json");		   
					given()
						.proxy("internet.ford.com", 83)
						.baseUri("https://reqres.in/api/users")
						.header("Content=Type","application/json")
						.contentType(ContentType.JSON)
						.body(file)
					.when()						
						.post()
					.then()
						.statusCode(201)
						.log().all();					   
	   }
	
}






