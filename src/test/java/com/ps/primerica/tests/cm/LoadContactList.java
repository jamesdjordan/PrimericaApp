package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class LoadContactList extends AbstractCMTests {

	public LoadContactList(WebDriver driver) {
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
