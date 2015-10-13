package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

import com.ps.primerica.components.DateComponent;
import com.ps.primerica.pages.CMPage;
import com.ps.primerica.pages.MenuPage;
import com.ps.primerica.tests.AbstractBaseTests;

public abstract class AbstractCMTests extends AbstractBaseTests {

    protected CMPage cmPage;
    protected MenuPage menuPage;
    protected DateComponent dateFormPage;
	protected String userName = "ATestUser";
	
	public AbstractCMTests(WebDriver driver) {
		super(driver);
		cmPage = new CMPage(driver);
		menuPage = new MenuPage(driver);
		dateFormPage = new DateComponent(driver);
	}

}
