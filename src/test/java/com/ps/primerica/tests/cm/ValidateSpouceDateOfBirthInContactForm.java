package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class ValidateSpouceDateOfBirthInContactForm extends AbstractCMTests {

	public ValidateSpouceDateOfBirthInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
    	cmPage.validateSpouceDateOfBirthInContactForm();
 	}
}