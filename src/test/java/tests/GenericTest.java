package tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import Pages.LoginPage;

public class GenericTest {
	public WebDriver driver = null;
	
	@BeforeMethod
	public void getDriver() {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + File.separator
				+ "drivers"
				+ File.separator
				+ "Windows" + File.separator
				+ "chrome" + File.separator
				+ "chromedriver.exe");
		
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "drivers"
				+ File.separator + "Windows" + File.separator + "firefox" + File.separator + "geckodriver.exe");
		
		
		this.driver =  new ChromeDriver();
			
		
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
	
	
	
	
	
	
	
	public static final String BROWSER = "Chrome";

	public String getBrowserType(String browser) {
		if (!browser.isEmpty()) {
			return browser;
		}
		String currentBrowser = System.getProperty("browser");
		if (driver == null) {
			return BROWSER;
		} else {
			return currentBrowser;
		}
	}

	public WebDriver getBrowser(String browser) {
		String BrowserType = getBrowserType(browser);

		switch (BrowserType) {
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "drivers"
					+ File.separator + getOSName() + File.separator + BrowserType + File.separator + "chromedriver.exe");
			return new ChromeDriver();
		case "Firefox":
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "drivers"
					+ File.separator + getOSName() + File.separator + BrowserType + File.separator + "geckodriver.exe");
			return new FirefoxDriver();
		case "Edge":
			if (System.getProperty("os.name").contains("Mac OS")) {
				throw new IllegalStateException("Edge browser is unsupported for Mac OS, please change browser");
			}
			System.setProperty("webdriver.msedge.driver", System.getProperty("user.dir") + File.separator + "drivers"
					+ File.separator + getOSName() + File.separator + BrowserType + File.separator + "msedgedriver");
			return new EdgeDriver();
		case "Safari":
			if (System.getProperty("os.name").contains("Windows") || System.getProperty("os.name").contains("Linux")) {
				throw new IllegalStateException("Safari browser is unsupported for " + System.getProperty("os.name")
						+ ", please change browser");
			}
			return new SafariDriver();
		default:
			throw new IllegalStateException("Unsupported browser");
		}

	}

	public String getOSName() {
		String OSName = (System.getProperty("os.name"));
		if (OSName.contains("Mac OS")) {
			return "macOS";
		} else if (OSName.contains("Windows")) {
			return "Windows";
		} else if (OSName.contains("Linux")) {
			return "Linux";
		} else {
			throw new IllegalStateException("Unknown OS name");
		}
	}
}
