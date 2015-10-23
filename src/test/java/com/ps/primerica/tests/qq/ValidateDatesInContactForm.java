package com.ps.primerica.tests.qq;

import org.openqa.selenium.WebDriver;

public class ValidateDatesInContactForm extends AbstractQQTests {

	public ValidateDatesInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
    	qqPage.validateDatesInContactForm(contact);
	}
}