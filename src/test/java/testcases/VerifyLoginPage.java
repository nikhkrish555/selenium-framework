package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import dataProvider.CsvDataProvider;
import factory.BrowserFactory;
import factory.DataProviderFactory;
import pages.HomePage;
import pages.LoginPage;

public class VerifyLoginPage {

	private WebDriver driver;
	BrowserFactory browser = new BrowserFactory();

	@BeforeMethod
	public void setup() {
		driver = browser.getBrowser("qa", "chrome");
		driver.get(DataProviderFactory.getConfig().getApplicationURL("qa", "url", "http://demo.avactis.com/5.0.1/"));
	}

	@Test
	public void testLoginPage() throws InterruptedException {
		HomePage home = PageFactory.initElements(driver, HomePage.class);
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);
		CsvDataProvider cs = new CsvDataProvider();
		home.clickOnSignInLink();
		login.loginApplication(cs.getTestData("username"), cs.getTestData("password"));
		login.signOutApplication();
	}
	
	@AfterMethod
	public void tearDown() {
		browser.closeBrowser(driver);
	}

}