package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelDataReader;
import utilities.ExtentReport;

public class BaseClass extends ExtentReport{

	public ChromeDriver driver;
	public Properties prop;
	public Select select;
	public FileInputStream file;
	public WebDriverWait wait;
	public WebElement element;
	public Actions action;
	public JavascriptExecutor js;

	@BeforeTest
	public void startApp() throws IOException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://insurance.policyexpert.co.uk/home");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		js = (JavascriptExecutor) driver;
		file = new FileInputStream("./src/main/resources/repository/ObjectRepository.properties");
		prop = new Properties();
		prop.load(file);
		action = new Actions(driver);
	}

	@AfterTest
	private void closeBrowser() {
		driver.close();
		driver.quit();
	}

	@DataProvider(name = "fetchData")
	public String[][] getData() throws IOException {
		ExcelDataReader reader = new ExcelDataReader();
		return reader.getTestData();
	}

	public BaseClass selectValue(String locator, String text) {
		try {
			element = driver.findElement(By.xpath(prop.getProperty(locator)));
			js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", element);
			wait.until(ExpectedConditions.visibilityOf(element));
			new Select(element).selectByValue(text);
			reportStep("The value '"+text +"' selected successfully","pass");
		} catch (Exception e) {
			reportStep("Failed while selecting the value '" + text+"'","fail");
			e.printStackTrace();
		}
		return this;

	}

	public BaseClass setText(String locator, String text) {
		try {
			element = driver.findElement(By.xpath(prop.getProperty(locator)));
			js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", element);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.click();
			element.clear();
			element.sendKeys(text);
			reportStep("The text '"+ text +"' entered successfully","pass");
		} catch (Exception e) {
			reportStep("Failed while entering text '" +text+"'","fail");
			e.printStackTrace();
		}
		return this;
	}

	public BaseClass clickButton(String locator) {
		try {
			element = driver.findElement(By.xpath(prop.getProperty(locator)));
			String text = element.getText();
			js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", element);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.click();
			reportStep("The button '"+text+"' clicked successfully","pass");
		} catch (Exception e) {
			reportStep("Failed while clicking the button '"+element.getText(),"fail");
			e.printStackTrace();
		}
		return this;
	}

	public BaseClass selectListItem(String locator) {
		try {
			element = driver.findElement(By.xpath(prop.getProperty(locator)));
			action.moveToElement(element).click().perform();
			reportStep("Element selected from list successfully","pass");
		} catch (Exception e) {
			reportStep("Failed while selecting element from the list","fail");
			e.printStackTrace();
		}
		return this;

	}

}
