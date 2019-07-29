package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tests.GenericTest;

public class LoginPage extends GenericTest {
	// private WebDriver pageDriver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "user-name")
	private WebElement usernameInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(css = "input[value='LOGIN']")
	private WebElement LoginButton;

	public ProductsPage loginAs(String usermane, String password) {
		usernameInput.sendKeys(usermane);
		passwordInput.sendKeys(password);
		LoginButton.click();
		return new ProductsPage(this.driver);
	}

	@FindBy(xpath = "//div[@class = 'bm-burger-button']")
	private WebElement MenuButton;

	@FindBy(id = "logout_sidebar_link")
	private WebElement LogoutButton;

	public void logout() {
		MenuButton.click();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}
		LogoutButton.click();
	}

	@FindBy(css = "h3[data-test='error']")
	private WebElement ErrorMessage;

	public String GetErrorMessage() {
		try {
			if (ErrorMessage.isDisplayed()) {
				return ErrorMessage.getText();
			}
			return "";
		} catch (Exception e) {
			return "";
		}
	}
}
