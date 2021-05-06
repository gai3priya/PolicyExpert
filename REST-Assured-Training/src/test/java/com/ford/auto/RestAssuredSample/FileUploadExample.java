package com.ford.auto.RestAssuredSample;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import java.io.File;


public class FileUploadExample {
	
	@Test
	public void testCase_FileUpload() {		
		
		String filePath = System.getProperty("user.dir")+"/src/test/java/FileUpload/SampleTextFile.txt";
		File fileToUpload = new File(filePath);
		
						given()
							.proxy("internet.ford.com", 83)
							//.multiPart("file", fileToUpload, "multipart/form-data")
						.when()
							.post("https://the-internet.herokuapp.com/upload")						
						.then()
							.assertThat().statusCode(200);
	}
}
