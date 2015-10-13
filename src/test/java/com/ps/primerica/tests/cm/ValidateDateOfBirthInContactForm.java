package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class ValidateDateOfBirthInContactForm extends AbstractCMTests {

	public ValidateDateOfBirthInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
    	cmPage.validateDateOfBirthInContactForm();
 	}
}