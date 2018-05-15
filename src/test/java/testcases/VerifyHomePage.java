package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import factory.BrowserFactory;
import factory.DataProviderFactory;
import pages.HomePage;

public class VerifyHomePage {

	private WebDriver driver;
	BrowserFactory browser = new BrowserFactory();

	@BeforeMethod
	public void setup() {
		driver = browser.getBrowser("qa", "chrome");
		driver.get(DataProviderFactory.getConfig().getApplicationURL("qa", "url", "http://demo.avactis.com/5.0.1/"));
	}

	@Test
	public void testHomePage() {
		HomePage home = PageFactory.initElements(driver, HomePage.class);
		String title = home.getApplicationTitle();
		Assert.assertTrue(title.contains("Avactis"));
	}
	
	@AfterMethod
	public void tearDown() {
		browser.closeBrowser(driver);
	}

}