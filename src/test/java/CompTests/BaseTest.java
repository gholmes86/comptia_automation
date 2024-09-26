package CompTests;

import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;



/**
 * Base class for the tests.
 */
public class BaseTest implements Config {

	protected WebDriver driver;
	protected static final String Jenkins ="ci/cd";

	// junit rule
	@Rule
	public ExternalResource resource = new ExternalResource() {
		@Override
		protected void before() throws Throwable {
			if (host.equals(Jenkins)) {
				if (browser.equals("chrome")) {
					WebDriverManager.chromedriver().setup();
					WebDriverManager.chromedriver().getDriverVersions();

					ChromeOptions options = new ChromeOptions();
					driver = new ChromeDriver(options);
					options.addArguments("--kiosk");
					options.addArguments("--start-maximized");
					options.addArguments("--incognito");
				options.addArguments("--headless");
				}
				else if (browser.equals("firefox")) {
					WebDriverManager.firefoxdriver().setup();
					FirefoxOptions options = new FirefoxOptions();
					options.addPreference("dom.file.createInChild", true);
					driver = new FirefoxDriver(options);
				}
			}
			else if (host.equals("localhost")) {
				switch (browser) {
					case "firefox": {
						WebDriverManager.firefoxdriver().setup();
						FirefoxOptions options = new FirefoxOptions();
						driver = new FirefoxDriver(options);
						driver.manage().window().maximize();
						break;
					}
					case "chrome": {
						System.out.print(WebDriverManager.chromedriver().getDriverVersions());
						WebDriverManager.chromedriver().browserVersion("129.0.6668.71").setup();

						ChromeOptions options = new ChromeOptions();
						options.addArguments("--kiosk");
						options.addArguments("--no-sandbox"); // Bypass OS security model

		//				options.addArguments("--headless");

						options.setExperimentalOption("useAutomationExtension", false);
						options.addArguments("--incognito");
						//options.addArguments("--start-maximized"); // open Browser in maximized mode
						options.addArguments("--window-size=4000,3000");

						options.addArguments("--disable-infobars");

						options.addArguments("--disable-extensions");

						options.addArguments("--disable-gpu");

						options.addArguments("--disable-dev-shm-usage");

						driver = new ChromeDriver(options);
						driver.manage().window().maximize();
						break;
					}
					case "Edge": {
						WebDriverManager.edgedriver().setup();
						EdgeOptions options = new EdgeOptions();
						driver = new EdgeDriver(options);
						driver.manage().window().maximize();
						break;
					}
				}
			}
		}

		@Override
		protected void after() {

			driver.close();

		}
	};
}
