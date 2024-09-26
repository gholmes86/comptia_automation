package CompTests;
import CompPageObjects.*;
import org.junit.Before;
import org.junit.Test;

public class ChromeSettingsTest extends BaseTest {
	private ChromeSettingsPage chromeSettingsPage;

	@Before
	public void setup() {
		chromeSettingsPage = new ChromeSettingsPage(driver);
	}

	@Test
	public void chromeSettingsTest_shouldBlockPopupsAndManageSites() {
		chromeSettingsPage.switchToIframe();
		chromeSettingsPage.navigateToPopupsSettings();
		chromeSettingsPage.blockPopupsAndRedirects();
		chromeSettingsPage.removeAllowedSite();
		chromeSettingsPage.blockSpecificSite("virusclickjacking4u.org");
		chromeSettingsPage.verifyBlockedSite("virusclickjacking4u.org");
	}
}
