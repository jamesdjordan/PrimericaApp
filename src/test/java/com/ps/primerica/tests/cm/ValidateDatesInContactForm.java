package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class ValidateDatesInContactForm extends AbstractCMTests {

	public ValidateDatesInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		menuPage.clickOnItemInContextMenu("Edit Contact");
		cmPage.validateDatesInContactForm(contact);
 	}
}