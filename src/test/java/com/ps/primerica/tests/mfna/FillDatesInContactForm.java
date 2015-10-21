package com.ps.primerica.tests.mfna;

import org.openqa.selenium.WebDriver;

public class FillDatesInContactForm extends AbstractMFNATests {

	public FillDatesInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
    	mfnaPage.fillDatesInContactForm();
	}
}
