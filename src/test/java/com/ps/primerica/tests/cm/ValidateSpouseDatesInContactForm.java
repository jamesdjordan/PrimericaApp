package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class ValidateSpouseDatesInContactForm extends AbstractCMTests {

	public ValidateSpouseDatesInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		menuPage.clickOnItemInContextMenu("Edit");
    	cmPage.validateSpouseDatesInContactForm();
    	cmPage.saveForm();
 	}
}
