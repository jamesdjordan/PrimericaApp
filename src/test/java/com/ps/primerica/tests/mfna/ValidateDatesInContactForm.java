package com.ps.primerica.tests.mfna;

import org.openqa.selenium.WebDriver;

public class ValidateDatesInContactForm extends AbstractMFNATests {

	public ValidateDatesInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
    	mfnaPage.validateDatesInContactForm(contact);
	}
}
