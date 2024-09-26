package CompPageObjects;

import CompTests.Config;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public class BasePage implements Config {

	protected WebDriver driver;
	private WebElement element;
	protected final int waitTimeOutSeconds;

	public BasePage(WebDriver driver, int waitTimeOutSeconds) {
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

	public List<WebElement> findElements(By by) {
		return driver.findElements(by);
	}

	public void type(String inputText, By locator) {
		find(locator).sendKeys(inputText);
	}

	public boolean elementIsDisplayed(By by) {
		return waitUntilElementIsVisible(by).isDisplayed();
	}

	public WebElement waitUntilElementIsVisible(By by) {
		return new WebDriverWait(driver, Duration.ofSeconds(waitTimeOutSeconds))
				.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/**
	 * Finds a single element using Selenium's findElement.
	 *
	 * @param locator
	 * @return
	 */
	public WebElement find(By locator) {
		return driver.findElement(locator);
	}

	public void clickElement(WebDriver driver, By by, int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// Wait for element to be present in DOM
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			// Scroll element into view
			WebElement element = driver.findElement(by);
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			// Wait for element to be clickable
			wait.until(ExpectedConditions.elementToBeClickable(by));
			js.executeScript("arguments[0].click();", element);
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
	}

	public Boolean isDisplayed(By locator, Integer timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (org.openqa.selenium.TimeoutException exception) {
			return false;
		}
		return true;
	}
}
