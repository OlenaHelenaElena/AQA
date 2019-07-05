package tests;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LoginTest {
	@Test
	public void testLoginToApp () {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
				+ File.separator + "drivers" + File.separator + "chromedriver.exe");
		WebDriver driver = new ChromeDriver(); 
		
		driver.get("https://www.saucedemo.com");
		
		driver.findElement(By.id("user-name")).sendKeys("standard_user");

		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		driver.findElement(By.cssSelector("input[value='LOGIN']")).click();
		
		
	}

	

}
