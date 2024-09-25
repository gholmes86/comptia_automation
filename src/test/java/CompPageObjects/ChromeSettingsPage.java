package CompPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class ChromeSettingsPage extends BasePage {

	// Locators

	private By chromeIcon = By.cssSelector("[data-automationid='GoogleChrome']");
	private By settingsIcon = By.cssSelector("#btnSettings\\.LayoutRoot\\.imImage");
	private By settingsMenu = By.xpath("//div[text()='Settings']");
	private By privacyAndSecurity = By.xpath("//div[text()='Privacy and security']");
	private By siteSettings = By.xpath("//div[text()='Site settings']");
	private By popupsAndRedirects = By.xpath("//div[text()='Pop-ups and redirects']");
	private By popupsSettingToggle = By.cssSelector("input[name='popupsBlocked'][value='block']");
	private By allowedSitesButton = By.xpath("//button[contains(text(),'Allowed')]");
	private By removeSiteButton = By.cssSelector("[data-automationid='openMenu']");
	private By removeOption = By.cssSelector("[data-automationid^='menu-item-remove']");
	private By addBlockButton = By.cssSelector("[data-automationid='add-block']");
	private By inputField = By.cssSelector("[data-automationid='editsite-input']");
	private By addConfirmButton = By.cssSelector("[data-automationid='editsite-add']");

	public ChromeSettingsPage(WebDriver driver) {
		super(driver,35);
		visit("/");
	}

	public void switchToIframe() {
		driver.switchTo().frame("labContent"); // Switching to iframe by its ID
		driver.switchTo().frame("simFrameContent"); // Switching to iframe by its ID

	}

	public void navigateToPopupsSettings() {
		elementIsDisplayed(chromeIcon);
		clickElement(driver,chromeIcon,20);
		elementIsDisplayed(settingsIcon);
		find(settingsIcon).click();
		elementIsDisplayed(settingsMenu);
		find(settingsMenu).click();
		elementIsDisplayed(privacyAndSecurity);
		find(privacyAndSecurity).click();
		elementIsDisplayed(siteSettings);
		find(siteSettings).click();
		elementIsDisplayed(popupsAndRedirects);
		find(popupsAndRedirects).click();
	}

	public void blockPopupsAndRedirects() {
		find(popupsSettingToggle).click();
		System.out.println("Pop-ups and redirects have been blocked.");
	}

	public void removeAllowedSite() {
		find(removeSiteButton).click();
		elementIsDisplayed(removeOption);
		find(removeOption).click();
		System.out.println("Allowed site 'doctorevilskeylogger.com' has been removed.");
	}

	public void blockSpecificSite(String siteUrl) {
		find(addBlockButton).click();
		elementIsDisplayed(inputField);
		type(siteUrl, inputField);
		find(addConfirmButton).click();
		System.out.println("Site '" + siteUrl + "' has been blocked.");
	}

	public boolean verifyBlockedSite(String siteUrl) {
		String formattedUrl = siteUrl.replace(".", "-").replace("*", ""); // Format the site URL to match the expected attribute pattern
		By blockedSiteVerification = By.cssSelector("[data-automationid='icontext-" + formattedUrl + "']");
		boolean isDisplayed = isDisplayed(blockedSiteVerification);
		assertTrue("Site '" + siteUrl + "' should be displayed as blocked.", isDisplayed);
		return isDisplayed;
	}
}
