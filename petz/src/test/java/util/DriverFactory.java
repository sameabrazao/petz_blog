package util;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.runner.notification.RunListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory extends RunListener {
	public static WebDriver driver = null;
	DesiredCapabilities capabilities = null;
	static Logger logger = Logger.getLogger(DriverFactory.class.toString());

	public static String parametro;
	public static String usuarioParametro;	

		public WebDriver openBrowser() throws Exception {
		System.setProperty("webdriver.chrome.verboseLogging", "false");
		if (driver != null) {
			return driver;
		}
		String browser = System.getProperty("browser");
		if (browser == null) {
			browser = "chrome";
		}
		logger.info("Configurando o browser : " + browser);
		if (browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			capabilities = DesiredCapabilities.internetExplorer();
		}
		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			capabilities = DesiredCapabilities.firefox();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--browser-test");
			capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
		}
		if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			capabilities = DesiredCapabilities.edge();
		}
		if (browser.equalsIgnoreCase("opera")) {
			WebDriverManager.operadriver().setup();
			capabilities = DesiredCapabilities.opera();
			OperaOptions options = new OperaOptions();
			options.addArguments("--brouser-test");
			capabilities.setCapability(OperaOptions.CAPABILITY, options);
		}
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			//options.setHeadless(true);
			options.addArguments("--browser-test");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			//driver = new ChromeDriver(options);
		}
//		driver = new RemoteWebDriver(new URL(new PropertyReader().readProperty("host.execucao")), capabilities);
		driver = new ChromeDriver();
//		driver = new FirefoxDriver();
//		driver = new OperaDriver();
//		driver = new EdgeDriver();
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);		
		if (driver == null) {
			throw new Exception("Browser inválido" + browser);
		}
		logger.info("browser: " + browser + " configurado......");
		return driver;
	}
}