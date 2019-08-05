package com.agrostar.automation.AmazonAutomation;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Parameters;

import org.testng.asserts.SoftAssert;
import org.testng.Assert;

/**<b>Name:</b> Amazon_BaseClass<br>
 * <b>Description:</b>
 * This Class is to create a driver and set up required test
 *
 */
public class Amazon_BaseClass {

	WebDriver driver;
	public SoftAssert sAssert;

	/**
	 * This function will execute before each Test tag in testng.xml
	 * 
	 * @param browser
	 * @throws Exception
	 */
	@BeforeTest
	@Parameters("browser")
	public void setupBrowser(String browser) throws Exception {

		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", FrameWorkConstants.BROWSEREXE_PATH + "/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", FrameWorkConstants.BROWSEREXE_PATH + "/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", FrameWorkConstants.BROWSEREXE_PATH + "/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else {

			throw new Exception("Browser is not correct");
		}
		driver.manage().timeouts().implicitlyWait(FrameWorkConstants.WEBDRIVER_WAIT_TIMEOUT, TimeUnit.SECONDS);
	}
	/**
	 * This function will execute After each Test tag in testng.xml
	 * 
	 * @param browser
	 * @throws Exception
	 */
	@AfterTest
	@Parameters("browser")
	public void closeBrowser(String browser) throws Exception {
		driver.close();
		driver.quit();
	}
}
