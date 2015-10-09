package com.ps.primerica.config;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.common.collect.Lists;

public class PrimericaProvider  {
	private final static String LOCAL_BROWSERS = "browsers.json";
    private static PrimericaProvider _primericaProvider;

    private List<WebDriver> drivers = Lists.newArrayList();
	private ObjectMapper mapper = new ObjectMapper();
	
	
	ExecutorService service = Executors.newFixedThreadPool(4);
	
    private PrimericaProvider() {
    	
		List<Map<String, String>> browsers = getSauceOndemandBrowsers();
    	if (CollectionUtils.isEmpty(browsers)) {
    		browsers = getLocalBrowsers();
    	}
    	
  		List<Future<WebDriver>> futures = Lists.newArrayList();
		for (Map<String, String> browserProperties : browsers) {
	    	final DesiredCapabilities desiredCapabilities = generateCommonDesiredCapabilities();
    		String os = browserProperties.get("os");
    		String browser = browserProperties.get("browser");
    		String version = browserProperties.get("version");
   	        desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
	   	    if (version != null) {
	   	    	desiredCapabilities.setCapability(CapabilityType.VERSION, version);
	        }
	   	    desiredCapabilities.setCapability(CapabilityType.PLATFORM, os);
            desiredCapabilities.setCapability("name",  getName(os, browser, version));

            Callable<WebDriver> initDriverTask = new Callable<WebDriver>(){
				public WebDriver call() throws Exception {
					WebDriver driver = new RemoteWebDriver(getUrl(), desiredCapabilities);
                    waitForContext(driver);
					return driver;
				}
    		};
    		Future<WebDriver> future = service.submit(initDriverTask);
    		futures.add(future);
		}    
		for (Future<WebDriver> future : futures) {
			try {
				drivers.add(future.get());
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				e1.printStackTrace();
			}
		}
    }


    
	private DesiredCapabilities generateCommonDesiredCapabilities() {
    	DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		return desiredCapabilities;
	}
	
	private URL getUrl() {
 		String sauceUserName = 
 				EnvironmentUtils.readPropertyOrEnv("SAUCE_USER_NAME", System.getenv().get("SAUCE_USERNAME"));
		String sauceAccessKey = 
				EnvironmentUtils.readPropertyOrEnv("SAUCE_API_KEY", System.getenv().get("SAUCE_ACCESS_KEY"));
		String host = EnvironmentUtils.readPropertyOrEnv("SELENIUM_HOST", 
        		ApplicationProperties.getInstance().getProperty(ApplicationConstants.DESIRED_CAPABILITIES_SELENIUM_HOST) );
        String port = EnvironmentUtils.readPropertyOrEnv("SELENIUM_PORT", 
        		ApplicationProperties.getInstance().getProperty(ApplicationConstants.DESIRED_CAPABILITIES_SELENIUM_PORT) );
    	if (StringUtils.isEmpty(port)) {
    		port = "80";
    	}     
    	URL url = null;
        try {
			url = new URL("http://" + sauceUserName + ":" + sauceAccessKey + 
			    			"@" + host + ":" + port + "/wd/hub");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        return url;
	}
	
	private String getName(String os, String browser, String version) {
        return ApplicationProperties.getInstance().getProperty(ApplicationConstants.DESIRED_CAPABILITIES_NAME) +
        		" ( " + os + " : " + browser + " : " + version + " ) ";
	}

	private void waitForContext(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
		//WebDriverWait wait = new WebDriverWait(driver, 600);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(WEBVIEW_ELEMENT));
	}

	private List<Map<String, String>> getSauceOndemandBrowsers() {
		String sauceOndemandBrowsers = EnvironmentUtils.readPropertyOrEnv("SAUCE_ONDEMAND_BROWSERS");
		List<Map<String,String>> browsers = null;
		try {
			if (sauceOndemandBrowsers != null) {
				browsers = mapper.readValue(sauceOndemandBrowsers, 
					    new TypeReference<List<Map<String,String>>>(){});
			}
		} catch (JsonParseException jpe) {
			jpe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return browsers;
	}
      
	
	private List<Map<String,String>> getLocalBrowsers() {
		List<Map<String,String>> browsers = null;
		try {
			browsers = mapper.readValue(this.getClass().
			          getClassLoader().getResource(LOCAL_BROWSERS), 
				    new TypeReference<List<Map<String,String>>>(){});
		} catch (JsonParseException jpe) {
			jpe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return browsers;
	}
	
    public List<WebDriver> getDrivers() {
    	return drivers;
    }
    
    public static PrimericaProvider getInstance() {
    	if (_primericaProvider == null) {
    		_primericaProvider = new PrimericaProvider();
    	}
    	return _primericaProvider; 
    }
}
