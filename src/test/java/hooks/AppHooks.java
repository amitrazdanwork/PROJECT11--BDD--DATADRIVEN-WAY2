package hooks;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import factory.DriverFactory;
import io.cucumber.java.*;
import utils.ConfigUtils;

public class AppHooks {
	String browserName, device, execution_type;
	private DriverFactory driverFactory;
	public WebDriver driver;                    //NOTE: This field should not be static, otherwise execution will not be going to work as expected..lots of errors and exceptions.
	private ConfigUtils configutil;
	public static String subfoldername;

	@Before
	public void Setup() throws MalformedURLException {
		configutil = new ConfigUtils();
		driverFactory = new DriverFactory();
		browserName = configutil.getBrowser();
		device=configutil.getDevice();
		execution_type=configutil.getExecutionType();
		driver = driverFactory.init_driver(execution_type, device, browserName);
	}

	@After(order = 0)
	public void TearDown(Scenario scenario) {
		driver.close();
	}

	@After(order = 1)
	public void FailedTestSS(Scenario scenario) {

		if (subfoldername == null) {

			LocalDateTime now = LocalDateTime.now();
			System.out.println("Before Formatting: " + now);
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
			subfoldername = now.format(format);
			System.out.println("After Formatting: " + subfoldername);

		}

		if (scenario.isFailed()) {

			// take screenshot and attach it to report file
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] SrcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(SrcFile, "image/png", screenshotName);

			// take screenshot and place the same under Screenshots folder
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			File trg = new File("./target/Screenshots/" + subfoldername + "/" + subfoldername + "_Failed" + ".png");
			try {
				FileUtils.copyFile(src, trg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Screenshot saved successfully !!!!!");

		}
	}


}
