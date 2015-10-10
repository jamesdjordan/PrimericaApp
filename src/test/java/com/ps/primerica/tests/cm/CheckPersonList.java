package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class CheckPersonList extends AbstractCMTests {

	public CheckPersonList(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		menuPage.gotoHomeContext();
    	menuPage.waitAndClickOnHamburger();
    	menuPage.clickOnContactManager();
    	cmPage.waitForPersonList();
    	cmPage.checkPersonList();
	}
}
