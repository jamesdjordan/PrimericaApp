package com.ps.primerica.tests.login;

import org.openqa.selenium.WebDriver;

import com.ps.primerica.config.ApplicationConstants;
import com.ps.primerica.config.ApplicationProperties;
import com.ps.primerica.pages.LoginPage;
import com.ps.primerica.tests.AbstractBaseTests;

public abstract class AbstractLoginTests extends AbstractBaseTests {

	protected final static String USERNAME = 
    		ApplicationProperties.getInstance().getProperty(ApplicationConstants.APPLICATION_USERNAME);
    protected final static String PASSWORD = 
    		ApplicationProperties.getInstance().getProperty(ApplicationConstants.APPLICATION_PASSWORD);

    protected LoginPage loginPage;

	public AbstractLoginTests(WebDriver driver) {
		super(driver);
		loginPage = new LoginPage(driver);
	}
}
