package tests;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Generic2 {

	public WebDriver driver = null;

	public static final String BROWSER = "Chrome";

	public String getBrowserType() {
		String browser = System.getProperty("browser");
		if (driver == null) {
			return BROWSER;
		} else {
			return browser;
		}
	}

	public WebDriver getBrowser() {
		String BrowserType = getBrowserType();

		switch (BrowserType) {
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "drivers"
					+ File.separator + getOSName() + File.separator + BrowserType + File.separator + "chromedriver");
			return new ChromeDriver();
		case "Firefox":
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "drivers"
					+ File.separator + getOSName() + File.separator + BrowserType + File.separator + "geckodriver");
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
