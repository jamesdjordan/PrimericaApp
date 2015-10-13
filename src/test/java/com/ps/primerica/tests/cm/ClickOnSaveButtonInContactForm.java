package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class ClickOnSaveButtonInContactForm extends AbstractCMTests {

	public ClickOnSaveButtonInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
    	cmPage.clickOnSaveButtonInContactForm();
	}
}