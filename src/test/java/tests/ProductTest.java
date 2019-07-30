package tests;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

import com.google.common.collect.Ordering;

import Pages.LoginPage;
import Pages.ProductsPage;

public class ProductTest extends GenericTest {

	@Test
	public void testOneOrderInCart() {
		LoginPage logPage = openLoginPage();
		ProductsPage prodPage = logPage.loginAs("standard_user", "secret_sauce");

		// buying
		driver.findElement(By.xpath("//button[@class='btn_primary btn_inventory']")).click();
		
		// go to cart
		driver.findElement(By.cssSelector("a[class='shopping_cart_link fa-layers fa-fw']")).click();
		
		boolean removeButtonExist = true;
		
		// looking for Remove button
		try {
			removeButtonExist = driver.findElement(By.cssSelector("button[class='btn_secondary cart_button']")).isDisplayed();
		} catch (Exception e) {
			removeButtonExist = false;
		}
		
		Assert.assertTrue(removeButtonExist);
	}
	
	@Test
	public void testThreeOrdersInCart() {
		LoginPage logPage = openLoginPage();
		ProductsPage prodPage = logPage.loginAs("standard_user", "secret_sauce");

		// buying
		driver.findElement(By.xpath("//button[@class='btn_primary btn_inventory']")).click();
		driver.findElement(By.xpath("//button[@class='btn_primary btn_inventory']")).click();
		driver.findElement(By.xpath("//button[@class='btn_primary btn_inventory']")).click();
		
		// go to cart
		driver.findElement(By.cssSelector("a[class='shopping_cart_link fa-layers fa-fw']")).click();
		
		int removeButtonNumber = 0;
		
		// looking for Remove buttons
		try {
			removeButtonNumber = driver.findElements(By.cssSelector("button[class='btn_secondary cart_button']")).toArray().length;
		} catch (Exception e) {
			removeButtonNumber = -1;
		}
		
		Assert.assertTrue(removeButtonNumber == 3);
	}
	
	@Test
	public void testSortingNameDown() {
		LoginPage logPage = openLoginPage();
		ProductsPage prodPage = logPage.loginAs("standard_user", "secret_sauce");	
		
		// sort by name (down)
		driver.findElement(By.xpath("//option[@value='az']")).click();
		
		List<WebElement> defaultList = driver.findElements(By.cssSelector("div[class='inventory_item_name']"));
		
		List<String> names = new ArrayList<>();
		for(int i =0; i < defaultList.size(); i++) {
			String productName = defaultList.get(i).getText();
			names.add(productName);
		}
		
		// checking ordering (ascending)
		Assert.assertTrue(Ordering.natural().isOrdered(names));
	}
	
	@Test
	public void testSortingNameUp() {
		LoginPage logPage = openLoginPage();
		ProductsPage prodPage = logPage.loginAs("standard_user", "secret_sauce");	
		
		// sort by name (up)
		driver.findElement(By.xpath("//option[@value='za']")).click();
		
		List<WebElement> defaultList = driver.findElements(By.cssSelector("div[class='inventory_item_name']"));
		
		List<String> names = new ArrayList<>();
		for(int i =0; i < defaultList.size(); i++) {
			String productName = defaultList.get(i).getText();
			names.add(productName);
		}
		
		// checking ordering (descending)
		Assert.assertTrue(Ordering.natural().reverse().isOrdered(names));
	}
	
	/*@Test
	public void testSortingPriceUp() {
		LoginPage logPage = openLoginPage();
		ProductsPage prodPage = logPage.loginAs("standard_user", "secret_sauce");	
		
		// sort by price (up)
		driver.findElement(By.xpath("//option[@value='lohi']")).click();
		
		List<WebElement> defaultList = driver.findElements(By.cssSelector("div[class='inventory_item_price']"));
		
		List<Integer> prices = new ArrayList<>();
		for(int i =0; i < defaultList.size(); i++) {
			String productPrice = defaultList.get(i).getText();
			String stop = "";
			//prices.add(productPrice);
		}
		
		// checking ordering (ascending)
		Assert.assertTrue(Ordering.natural().isOrdered(prices));
	}
	
	@Test
	public void testSortingPriceDown() {
		LoginPage logPage = openLoginPage();
		ProductsPage prodPage = logPage.loginAs("standard_user", "secret_sauce");	
		
		// sort by price (down)
		driver.findElement(By.xpath("//option[@value='hilo']")).click();
		
		List<WebElement> defaultList = driver.findElements(By.cssSelector("div[class='inventory_item_price']"));
		
		List<String> names = new ArrayList<>();
		for(int i =0; i < defaultList.size(); i++) {
			String productName = defaultList.get(i).getText();
			names.add(productName);
		}
		
		// checking ordering (descending)
		Assert.assertTrue(Ordering.natural().reverse().isOrdered(names));
	}*/
	
	/*@Test
	public void testCheckoutFirstName() {
		Assert.assertTrue(true );
	}
	
	@Test
	public void testCheckoutLastName() {
		Assert.assertTrue(true );
	}
	
	@Test
	public void testCheckoutZipCode() {
		Assert.assertTrue(true );
	}
	*/
}
