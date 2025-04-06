package factory;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

	public static WebDriver driver;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver init_driver(String execution_type, String device, String browser) throws MalformedURLException {

		if (execution_type.equalsIgnoreCase("local")) {

			switch (browser) {

			case "chrome":

				// driver instantiation
				ChromeOptions coptions = new ChromeOptions();
				coptions.addArguments("--remote-allow-origins=*");

				driver = new ChromeDriver(coptions);
				tlDriver.set(driver);

				break;

			case "edge":

				// driver instantiation
				EdgeOptions eoptions = new EdgeOptions();
				eoptions.addArguments("--remote-allow-origins=*");

				driver = new EdgeDriver(eoptions);
				tlDriver.set(driver);

				break;

			case "firefox":

				// driver instantiation
				FirefoxOptions foptions = new FirefoxOptions();
				foptions.addArguments("--remote-allow-origins=*");

				driver = new FirefoxDriver(foptions);
				tlDriver.set(driver);

				break;

			default:
				System.out.println("Wrong input for browser name");
				break;
			}

		} else if (execution_type.equalsIgnoreCase("remote")) {

			// Define the remote hub URL
			String HubUrl = "http://192.168.29.158:4444/";

			// Creating object of desired capabilities
			DesiredCapabilities cap = new DesiredCapabilities();

			/* Setting up platform */
			if (device.equals("windows")) {
				cap.setPlatform(Platform.WIN10); // if you are working on Windows 11
			} else if (device.equals("linux")) {
				System.out.println("Platform done");
				cap.setPlatform(Platform.LINUX); // if you are working on Linux
			} else {
				System.out.println("Invalid platform");
			}

			/* Setting up browser */
			if (browser.equals("chrome")) {
				cap.setBrowserName("chrome"); // if you want to execute on chrome
			} else if (browser.equals("edge")) {
				cap.setBrowserName("edge"); // if you want to execute on edge
				System.out.println("Browser got setup");
			} else if (browser.equalsIgnoreCase("firefox")) {
				cap.setBrowserName(browser);
			} else {
				System.out.println("Invalid browser");
			}

			/*
			 * ChromeOptions coptions = new ChromeOptions();
			 * 
			 * coptions.addArguments("--remote-allow-origins=*"); driver = new
			 * ChromeDriver(coptions); tlDriver.set(driver);
			 */

			// remote driver instantiation
			driver = new RemoteWebDriver(new URL("http://localhost:4444/"), cap);
			tlDriver.set(driver);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();

		return getDriver();

	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

}
