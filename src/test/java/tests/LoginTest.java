package tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.LoginPage;
import Pages.ProductsPage;

public class LoginTest extends GenericTest {

	@Test
	public void testLoginExistedUser() {
		LoginPage logPage = openLoginPage();
		ProductsPage prodPage = logPage.loginAs("standard_user", "secret_sauce");
		Assert.assertTrue(prodPage.isUserLoggedIn());

		SoftAssert soft = new SoftAssert();

		soft.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"),
				"Current URL should be https://www.saucedemo.com/inventory.html, but found https://www.saucedemo.com/inventory.htmlll ");
		logPage.logout();
	}

	@Test
	public void testLoginWithEmptyUserName() {
		LoginPage logPage = openLoginPage();
		ProductsPage prodPage = logPage.loginAs("", "secret_sauce");
		// Assert.assertFalse(prodPage.isUserLoggedIn());
		String message = logPage.GetErrorMessage();
		Assert.assertEquals(message, "Epic sadface: Username is required");
	}

	@Test
	public void testLoginWithEmptyPassword() {
		LoginPage logPage = openLoginPage();
		ProductsPage prodPage = logPage.loginAs("standard_user", "");
		// Assert.assertFalse(prodPage.isUserLoggedIn());
		String message = logPage.GetErrorMessage();
		Assert.assertEquals(message, "Epic sadface: Password is required");
	}

	@Test
	public void testLoginWithLockedUser() {
		LoginPage logPage = openLoginPage();
		ProductsPage prodPage = logPage.loginAs("locked_out_user", "secret_sauce");
		// Assert.assertFalse(prodPage.isUserLoggedIn());
		String message = logPage.GetErrorMessage();
		Assert.assertEquals(message, "Epic sadface: Sorry, this user has been locked out.");
	}

	@Test
	public void testLoginWithIncorectUser() {
		LoginPage logPage = openLoginPage();
		ProductsPage prodPage = logPage.loginAs("incorect", "unknown");
		// Assert.assertFalse(prodPage.isUserLoggedIn());
		String message = logPage.GetErrorMessage();
		Assert.assertEquals(message, "Epic sadface: Username and password do not match any user in this service");
	}

	@Test
	public void testLogout() {
		LoginPage logPage = openLoginPage();
		ProductsPage prodPage = logPage.loginAs("standard_user", "secret_sauce");
		Assert.assertTrue(prodPage.isUserLoggedIn());
		logPage.logout();
		Assert.assertFalse(prodPage.isUserLoggedIn());
	}

}


