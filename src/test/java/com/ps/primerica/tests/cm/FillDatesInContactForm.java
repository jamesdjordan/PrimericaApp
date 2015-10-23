package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class FillDatesInContactForm extends AbstractCMTests {

	public FillDatesInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		cmPage.fillDateFieldsInContactForm(contact);
		cmPage.fillRequiredFieldsInContactForm(contact);
		cmPage.saveForm();
	}
}
