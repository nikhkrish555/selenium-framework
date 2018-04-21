package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import dataProvider.CsvDataProvider;
import factory.BrowserFactory;
import factory.DataProviderFactory;
import pages.HomePage;
import pages.LoginPage;

public class VerifyLoginPageWithReport {

	private WebDriver driver;
	ExtentReports report;
	ExtentTest logger;

	@BeforeMethod
	public void setup() {
		report = new ExtentReports("./Reports/LoginPageReport.html", true);
		logger=report.startTest("Verify Login Test");
		driver = BrowserFactory.getBrowser("qa", "firefox");
		driver.get(DataProviderFactory.getConfig().getApplicationURL("qa", "url", "http://demo.avactis.com/5.0.1/"));
		logger.log(LogStatus.INFO, "Application is up and running");
	}

	@Test
	public void testLoginPage() throws InterruptedException {
		HomePage home = PageFactory.initElements(driver, HomePage.class);
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);
		CsvDataProvider cs = new CsvDataProvider();
		home.clickOnSignInLink();
		logger.log(LogStatus.INFO, "Clicked Sign In Link");
		login.loginApplication(cs.getTestData("username"), cs.getTestData("password"));
		logger.log(LogStatus.PASS, "Login Successful");
		login.signOutApplication();
		logger.log(LogStatus.PASS, "Logout Successful");
	}

	@AfterMethod
	public void tearDown() {
		BrowserFactory.closeBrowser(driver);
		report.endTest(logger);
		report.flush();
	}

}