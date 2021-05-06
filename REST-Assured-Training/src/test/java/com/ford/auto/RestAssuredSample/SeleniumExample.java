package com.ford.auto.RestAssuredSample;

import static io.restassured.RestAssured.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;

public class SeleniumExample {
	
	protected static String strState;
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	@Test
	public void testcase_SeleniumExample() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Utilities\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver,30);
		driver.manage().window().maximize();
		driver.navigate().to("https://www.ford.com/dealerships/");		
		
		WebElement optionLocation = driver.findElement(By.xpath("//input[@type='radio' and @value='location']/following-sibling::span"));
		optionLocation.click();
		
		//Call rest assured method and fetch the value of "State", assign to the "strState" variable
		RestAssuredTestCase_getState();
		System.out.println("Value of State fetched from REST Assured Method is: "+strState);
		
		WebElement txtSearch = driver.findElement(By.name("searchText"));
		txtSearch.clear();
		//Passing the "State" value to the GUI search text box.
		txtSearch.sendKeys(strState);
				
		WebElement searchResultItem;
		searchResultItem=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='fgx-brand-locateDealerTypeAheadResults']/li//span[text()='Michigan']")));		
		searchResultItem.click();
				
		WebElement dealerName;
		dealerName=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dealer-list']//span[@id='idx-1']/following-sibling::span")));
		
		String strDealerName = dealerName.getText();
		Assert.assertEquals(strDealerName,"Fox Ford Grand Traverse");
		driver.quit();
	}
	
	
	//RestAssured Testcase
	public static void RestAssuredTestCase_getState() {		
		String strResponse;
		strResponse = 
					given()
					.proxy("internet.ford.com", 83)
						.baseUri("http://zippopotam.us/us/48210")
					.when()
						.get()
					.then()
						.extract().response().asString();		
		JsonPath objJsonPath = new JsonPath(strResponse);
		strState = objJsonPath.getString("places.state[0]");		
	}

}
