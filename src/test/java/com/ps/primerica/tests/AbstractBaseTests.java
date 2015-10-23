package com.ps.primerica.tests;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.common.collect.Lists;
import com.ps.primerica.config.PrimericaProvider;
import com.ps.primerica.json.JsonMapper;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.Parallelized;
import com.saucelabs.junit.SauceOnDemandTestWatcher;

@RunWith(Parallelized.class)
public abstract class AbstractBaseTests implements SauceOnDemandSessionIdProvider {
	
	protected WebDriver driver;
	protected String userName = "ATestUser";
	protected JsonMapper mapper = new JsonMapper();

	public @Rule
    SauceOnDemandTestWatcher resultReportingTestWatcher;
	
	private SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication();


	public AbstractBaseTests(WebDriver driver) {
		this.driver = driver;
		String sauce = System.getenv().get("SAUCE");
      	boolean isSauceEnabled = (sauce == null) || 
      			(sauce.equals("null")) || !sauce.equalsIgnoreCase("false");
		if (isSauceEnabled) {
			resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);
		}
	}
	
	@Parameterized.Parameters
	public static List<Object[]> getDrivers() {
		List<Object[]> result = Lists.newLinkedList();
		List<WebDriver> drivers = PrimericaProvider.getInstance().getDrivers();
		for (WebDriver driver : drivers) {
			result.add(new Object[] {driver});
		}
		return result;
	}
	
	public String getSessionId() {
		return ((RemoteWebDriver)driver).getSessionId().toString();
	}
	
	@Test
	public void runTest() {
		executeTest();
	}

	public abstract void executeTest();

}

