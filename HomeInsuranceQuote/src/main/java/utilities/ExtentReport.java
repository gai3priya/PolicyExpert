package utilities;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport {

	public static ExtentHtmlReporter reporter;
	public static ExtentReports report;
	public ExtentTest test, node;
	public String testcaseName, author, testDescription;

	@BeforeSuite
	public void startReport() {

		reporter = new ExtentHtmlReporter("./src/main/resources/reports/result.html");
		reporter.setAppendExisting(true);
		report = new ExtentReports();
		report.attachReporter(reporter);
	}

	@BeforeClass
	public void createReport() {
		test = report.createTest(testcaseName, testDescription);
		test.assignAuthor(author);
	}

	public void reportStep(String desc, String status) {
		if (status.equalsIgnoreCase("pass"))
			test.pass(desc);
		else if (status.equalsIgnoreCase("fail"))
			test.fail(desc);
	}

	@AfterSuite
	public void stopReport() {

		report.flush();

	}
}
