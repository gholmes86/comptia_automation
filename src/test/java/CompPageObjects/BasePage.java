package CompPageObjects;


import CompTests.Config;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.jetty7.util.log.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class BasePage implements Config {

	protected WebDriver driver;
	private WebElement element;
	protected final int waitTimeOutSeconds;

	public BasePage(WebDriver driver,int waitTimeOutSeconds) {
		this.driver = driver;
		this.waitTimeOutSeconds = waitTimeOutSeconds;
	}


	public void visit(String url) {
		if (url.contains("http")) {
			driver.get(url);
		} else {
			driver.get(baseUrl + url);
		}
	}
	/**
	 * Pauses execution to adjust to running suite in different environments.
	 *
	 * @param i
	 */
	public void slowDown(int i) {
		try {
			TimeUnit.SECONDS.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public List<WebElement> findElements(By by) {
		return driver.findElements(by);
	}


	public boolean elementIsDisplayed(By by) {
		return waitUntilElementIsVisible(by).isDisplayed();
	}

	public WebElement waitUntilElementIsVisible(By by) {
		return new WebDriverWait(driver, waitTimeOutSeconds).until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/**
	 * finds a single element using the Selenium's findElement
	 *
	 * @param locator
	 * @return
	 */
	public WebElement find(By locator) {
		return driver.findElement(locator);
	}

	public void clickElement(WebDriver driver, By by, int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			//wait for element to be present in DOM
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			//scroll element into view
			WebElement element = driver.findElement(by);
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			//wait for element to be clickable
			wait.until(ExpectedConditions.elementToBeClickable(by));
			js.executeScript("arguments[0].click();", element);

		} catch (StaleElementReferenceException e) {
			Log.info("StaleElementReferenceException-" + e.getLocalizedMessage());
			e.printStackTrace();


		} catch (WebDriverException e) {
			Log.info("WebDriverException getMessage-" + e.getMessage());
			e.printStackTrace();
			Log.info("WebDriverException done");

		}
	}

	/**
	 * produces a list of elements and prints the attribute list
	 *
	 * @param locators
	 * @return
	 */
	public List<WebElement> finds(By locators) {
		List<WebElement> yourList = driver.findElements(locators);
		if (yourList != null || !yourList.isEmpty()) {
			for (WebElement webElement : yourList) {
			}
		} else {

		}
		return driver.findElements(locators);
	}

	public void max(){
		driver.manage().window().maximize();
	}

	/**
	 * finds multiple elements using the Selenium's findElements and clicking element using the seleinium's click method
	 *
	 * @param locators
	 * @param i
	 */
	public void click(By locators, int i) {
		finds(locators).get(i).click();
	}

	/**
	 * inputs text(inputText)into field element(locator)
	 *
	 * @param inputText
	 * @param locator
	 */
	public void type(String inputText, By locator) {
		
		find(locator).sendKeys(inputText);
	}

	public Boolean isDisplayed(By locator) {
		return find(locator).isDisplayed();
	}

	public Boolean isDisplayedArray(By locators, int i) {
		return finds(locators).get(i).isDisplayed();
	}

	//element not present
	public boolean isElementPresent(By locatorKey) {
		try {
			driver.findElement(locatorKey);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public Boolean isDisplayed(By locator, Integer timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (org.openqa.selenium.TimeoutException exception) {
			return false;
		}
		return true;
	}

	/**
	 * Switches into a given frame and allows selenium to interact with elements therein
	 * @param i
	 */
	public void switchToFrame(int i) {
		driver.switchTo().frame(i);
	}

	/**
	 *
	 */
	public void switchBackToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public void maximize() {
		driver.manage().window().maximize();
	}



}