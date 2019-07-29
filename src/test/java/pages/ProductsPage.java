package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {

	public ProductsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "shopping_cart_container")
	private WebElement shoppingCartIcon;

	public boolean isUserLoggedIn() {

		try {
			return shoppingCartIcon.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

//	public CartPage addProductToCart(List<String> productName) {
//
//		pageDriver
//				.findElement(By.xpath("//div[@class='inventory_item']//div[contains(text(), '" + productName
//						+ "')]//ancestor::div[@class='inventory_item']//button[@class='btn_primary btn_inventory']"))
//				.click();
//	}
//
//	public CartPage addProductToCart(String... productName) {
//		  for ()
//			 
//
//			pageDriver.findElement(By.xpath("//div[@class='inventory_item']//div[contains(text(), '" + productName
//					+ "')]//ancestor::div[@class='inventory_item']//button[@class='btn_primary btn_inventory']")).click();
//	  }

	public ProductsPage() {

	}

}
