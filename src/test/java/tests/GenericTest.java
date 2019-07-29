package tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import Pages.LoginPage;

public class GenericTest {
	public WebDriver driver = null;
	
	@BeforeMethod
	public void getDriver() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
				+ File.separator + "drivers" + File.separator + "chromedriver.exe");
		this.driver = new ChromeDriver(); 
		driver.get("https://www.saucedemo.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public LoginPage openLoginPage() {
		return new LoginPage(driver);
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}
}
