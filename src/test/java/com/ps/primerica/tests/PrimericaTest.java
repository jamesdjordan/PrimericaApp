package com.ps.primerica.tests;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ps.primerica.config.EnvironmentUtils;
import com.ps.primerica.config.PrimericaProvider;
import com.ps.primerica.tests.cm.CMSuiteTests;
import com.ps.primerica.tests.login.LoginSuiteTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	LoginSuiteTests.class,
	CMSuiteTests.class
})
public class PrimericaTest {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(PrimericaTest.class);
	private static List<WebDriver> drivers;

	@BeforeClass
	public static void setup() throws Exception {
       	LOGGER.info("Setup drivers : ");
		String seleniumHost = System.getenv().get("SELENIUM_HOST");
		String seleniumPort = System.getenv().get("SELENIUM_PORT");
		String seleniumPlatform = System.getenv().get("SELENIUM_PLATFORM");
		String seleniumVersion = System.getenv().get("SELENIUM_VERSION");
		String seleniumBrowser = System.getenv().get("SELENIUM_BROWSER");
		String seleniumDevice = System.getenv().get("SELENIUM_DEVICE");
		String seleniumDeviceType = System.getenv().get("SELENIUM_DEVICE_TYPE");
		String seleniumDriver = System.getenv().get("SELENIUM_DRIVER");
		String sauceOnDemandBrowsers = System.getenv().get("SAUCE_ONDEMAND_BROWSERS");
		String seleniumURL = System.getenv().get("SELENIUM_URL");
		String sauceUserName = EnvironmentUtils.readPropertyOrEnv("SAUCE_USER_NAME", System.getenv().get("SAUCE_USERNAME"));
		String sauceApiKey = EnvironmentUtils.readPropertyOrEnv("SAUCE_API_KEY", System.getenv().get("SAUCE_ACCESS_KEY"));
//		String sauceUserName = System.getenv().get("SAUCE_USER_NAME");
//		String sauceApiKey = System.getenv().get("SAUCE_API_KEY");
		String seleniumStartingURL = System.getenv().get("SELENIUM_STARTING_URL");
		
 /*      	LOGGER.info("seleniumHost : {}", seleniumHost);
       	LOGGER.info("seleniumPort : {}", seleniumPort);
       	LOGGER.info("seleniumPlatform : {}", seleniumPlatform);
       	LOGGER.info("seleniumVersion : {}", seleniumVersion);
       	LOGGER.info("seleniumBrowser : {}", seleniumBrowser);
       	LOGGER.info("seleniumDevice : {}", seleniumDevice);
       	LOGGER.info("seleniumDeviceType : {}", seleniumDeviceType);
       	LOGGER.info("seleniumDriver : {}", seleniumDriver);
       	LOGGER.info("sauceOnDemandBrowsers : {}", sauceOnDemandBrowsers);
       	LOGGER.info("seleniumURL : {}", seleniumURL);
       	LOGGER.info("sauceUserName : {}", sauceUserName);
       	LOGGER.info("sauceApiKey : {}", sauceApiKey);
       	LOGGER.info("seleniumStartingURL : {}", seleniumStartingURL);
*/		
		drivers = PrimericaProvider.getInstance().getDrivers();
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
       	LOGGER.info("Quit drivers");
       	if (!CollectionUtils.isEmpty(drivers)) {
           	for (WebDriver driver : drivers) {
        		if (driver != null) {
        			driver.quit();
        		}
           	}
       	}
	}
	
}
