package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	private WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath="//div[@class='header content']//a[contains(text(),'Sign In')]") WebElement signInLink;
	@FindBy(xpath="//div[@class='header content']//a[contains(text(),'Create an Account')][1]") WebElement createAccountLink;
	
	public void clickOnSignInLink() {
		signInLink.click();
	}
	
	public void clickOnCreateAccountLink() {
		createAccountLink.click();
	}
	
	public String getApplicationTitle() {
		return driver.getTitle();
	}
}
