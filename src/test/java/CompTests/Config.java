package CompTests;

public interface Config {
	final String baseUrl = System.getProperty("baseUrl", "https://testoutstagecontent.blob.core.windows.net/features-chrome2024-en-us/en-us/sims/typescriptv1/features-chrome2024-labs/simstartup_webpack.html?package=sims&sim=l_chrome_popups&dev=true&automation=true&capturelog=true");
	final String host = System.getProperty("host", "localhost");
	final String browser = System.getProperty("browser", "chrome");
}