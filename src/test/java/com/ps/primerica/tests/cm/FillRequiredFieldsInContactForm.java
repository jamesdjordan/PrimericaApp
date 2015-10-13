package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class FillRequiredFieldsInContactForm extends AbstractCMTests {

	public FillRequiredFieldsInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		cmPage.fillRequiredFieldsInContactForm();
	}
}
