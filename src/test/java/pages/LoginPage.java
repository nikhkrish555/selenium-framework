package pages;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage {
	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(id = "email")
	WebElement username;
	@FindBy(name = "login[password]")
	WebElement password;
	@FindBy(className = "primary")
	WebElement loginButton;

	By customerMenu = By.xpath("//div[@class='header content']//button[@class='action switch']");
	By signOut = By.xpath("//*[@class='header content']//div[@class='customer-menu']//li[@class='authorization-link']");

	public void loginApplication(String loginUsername, String loginPassword) {
		username.sendKeys(loginUsername);
		password.sendKeys(loginPassword);
		loginButton.click();
	}

	public void signOutApplication() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement downArrowButton = wait.until(ExpectedConditions.presenceOfElementLocated(customerMenu));
		downArrowButton.click();
		WebElement signOutElement = wait.until(ExpectedConditions.presenceOfElementLocated(signOut));
		Assert.assertEquals(signOutElement.getText(), "SIGN OUT");
		signOutElement.click();
	}
}
