package com.ps.primerica.tests.mfna;

import org.openqa.selenium.WebDriver;

public class FillRequiredFieldsInContactForm extends AbstractMFNATests {

	public FillRequiredFieldsInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
    	mfnaPage.fillRequiredFieldsInContactForm();
	}
}
