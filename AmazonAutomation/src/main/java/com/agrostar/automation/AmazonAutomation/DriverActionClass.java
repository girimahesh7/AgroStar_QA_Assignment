package com.agrostar.automation.AmazonAutomation;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverActionClass {
	WebDriver driver;
	WebDriverWait wait;
	Logger log = Logger.getLogger(DriverActionClass.class);

	DriverActionClass(WebDriver driver) {
		this.driver = driver;
	}

	public void openUrl(String url) {
		log.info("**************************Open Site**************************");
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	public void clickOnElement(WebElement element, String elementDescription) {

		log.info("**************************Clicking on Element**************************" + elementDescription);

		wait = new WebDriverWait(driver, 20);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			//Sometimes click is not working for Add Cart button added javascript to handle this situation
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

		}
	}

	public void scrollIntoViewElement(WebElement element, String elementDescription) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			log.info("Failed to scroll into view element: " + elementDescription);
			log.info("Exception in ng_scrollIntoViewElement: " + e.getMessage());
		}
	}

	public void setText(WebElement ele, String text) {
		log.info("Entering the Text for " + text);
		wait = new WebDriverWait(driver, 10);
		try {
			scrollIntoViewElement(ele, text);
			wait.until(ExpectedConditions.visibilityOf(ele));
			ele.sendKeys(text);
			ele.sendKeys(Keys.TAB);

		} catch (Exception e) {
			log.info("Unable to Enter tesxt for " + text);
		}
	}

	public String getText(WebElement element, String attributeName, String elementDescription) {
		String returnText = "";
		wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			returnText = element.getText();
			log.info("Text from " + "'" + elementDescription + "'" + " = " + returnText);
		} catch (Exception e) {
			log.info("Exception to get Text value: " + e.getMessage());

		}
		return returnText;
	}

	public void openAndSwitchToNewWindow() {
		String parent = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> I1 = s1.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);
					}
		}
	}

	public void selectDropDownValueByText(String text, WebElement element) {
		wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			Select select = new Select(element);
			select.selectByVisibleText(text);
		} catch (Exception e) {
			log.info("Exception to select the drop down value: " + e.getMessage());
		}
	}

	public String getSelectedDropDownValue(WebElement element) {
		String selectedText = null;
		try {
			Select select = new Select(element);
			WebElement option = select.getFirstSelectedOption();
			selectedText = option.getText();
			return selectedText;
		} catch (Exception e) {
			log.info("Exception to get the drop down value: " + e.getMessage());
		}
		return selectedText;
	}
}
