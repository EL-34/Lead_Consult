/*
	This is the Geb configuration file.
	
	See: http://www.gebish.org/manual/current/#configuration
*/

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities

waiting {
	timeout = 20
}

environments {

	// run via “./gradlew chromeTest”
	// See: http://code.google.com/p/selenium/wiki/ChromeDriver
	chrome {
		ChromeOptions cap = new ChromeOptions()
		//		DesiredCapabilities cap = DesiredCapabilities.chrome()
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver/linux_64/current")

		driver = {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized"); // open Browser in maximized mode
			options.addArguments("disable-infobars"); // disabling infobars
			options.addArguments("--disable-extensions"); // disabling extensions
			options.addArguments("--disable-gpu"); // applicable to windows os only
			options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
			options.addArguments("--no-sandbox", "--disable-dev-shm-usage"); // Bypass OS security model
			options.addArguments("--disable-dev-shm-usage");
			options.setExperimentalOption("useAutomationExtension", false);
			options.addArguments("--remote-allow-origins=*");
			def driverInstance = new ChromeDriver(options)
			if (Config.startMaximized) { driverInstance.manage().window().maximize() }
			driverInstance }
	}



	// run via “./gradlew chromeHeadlessTest”
	// See: http://code.google.com/p/selenium/wiki/ChromeDriver
	chromeHeadless {
		ChromeOptions cap = new ChromeOptions()
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver/linux_64/current")

		driver = {
			ChromeOptions o = new ChromeOptions()
			o.addArguments("--headless")
			o.addArguments("window-size=1920,1080")
			o.addArguments("ignore-certificate-errors")
			o.addArguments("--no-sandbox");
			o.addArguments("disable-gpu");
			new ChromeDriver(o)
		}
	}

	// run via “./gradlew firefoxTest”
	// See: http://code.google.com/p/selenium/wiki/FirefoxDriver
	firefox {
		atCheckWaiting = 1
		System.setProperty("webdriver.gecko.driver", "../../../drivers/geckodriver/linux_64/geckodriver_v0.30.0-linux64");
		System.setProperty("webdriver.firefox.bin", "/usr/bin/firefox");

		driver = {
			DesiredCapabilities cap = new FirefoxOptions()
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true)
			def driverInstance = new FirefoxDriver(cap)
			if (Config.startMaximized) { driverInstance.manage().window().maximize() }
			driverInstance }
	}
}

// To run the tests with all browsers just run “./gradlew test”

//noinspection GroovyUnusedAssignment
baseUrl = Config.baseUrl


