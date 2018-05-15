package factory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

	private WebDriver driver = null;

	public  WebDriver getBrowser(String environment, String browserName) {
		if (driver == null) {
			if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						DataProviderFactory.getConfig().getBrowserDriverPath(environment, "firefox"));
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						DataProviderFactory.getConfig().getBrowserDriverPath(environment, "chrome"));
				driver = new ChromeDriver();
			} else {
				System.setProperty("webdriver.gecko.driver",
						DataProviderFactory.getConfig().getBrowserDriverPath(environment, "firefox"));
				driver = new FirefoxDriver();
			}
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;

	}

	public void closeBrowser(WebDriver driver) {
		driver.close();
		driver.quit();
	}
}
