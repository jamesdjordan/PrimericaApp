package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class ValidateLastContactInContactForm extends AbstractCMTests {

	public ValidateLastContactInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
    	cmPage.validateLastContactInContactForm();
 	}
}