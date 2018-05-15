package utility;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class Helper {
	
	public static String captureScreenshot(WebDriver driver, String screenshotName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator
				+ screenshotName + System.currentTimeMillis() + ".png";
		try {
			FileUtils.copyFile(src, new File(destination));
		} catch (IOException e) {
			System.out.println("Failed to capture screenshot. " + e.getMessage());
		}
		return destination;
	}

	/** fluent wait - customized wait till expected element is displayed **/
	public static WebElement fluentWait(final String objID, final Long waitSec, WebDriver driver) {

		@SuppressWarnings("deprecation")
		Wait<WebDriver> fluentwait = new FluentWait<WebDriver>(driver).withTimeout(waitSec, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

		// logger.info("Fluent wait for element: "+objID+". Waiting for a maximum of
		// "+waitSec+" seconds.");

		WebElement element = fluentwait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(By.xpath(objID));
				element.isDisplayed();
				return element;
			}
		});
		return element;
	}

	/** fluent wait - customized wait till expected value for element is met **/
	public static WebElement fluentWaitUntilExpectedValue(WebDriver driver, final String objID, final Long waitSec,
			final String expectedValue) {

		@SuppressWarnings("deprecation")
		Wait<WebDriver> fluentwait = new FluentWait<WebDriver>(driver).withTimeout(waitSec, TimeUnit.SECONDS)
				.pollingEvery(1000, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

		// logger.info("Fluent wait for element: " + objID + ". Waiting for a maximum of
		// " + waitSec + " seconds.");

		WebElement element = fluentwait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(By.xpath(objID));
				String value = element.getText();
				if (expectedValue.equalsIgnoreCase(value)) {
					return element;
				} else {
					return null;
				}
			}
		});
		// logger.info("Element with value \" " + expectedValue + " \" displayed ?:" +
		// element.isDisplayed());
		return element;
	}
}
