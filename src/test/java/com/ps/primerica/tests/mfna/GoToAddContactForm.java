package com.ps.primerica.tests.mfna;

import org.openqa.selenium.WebDriver;

public class GoToAddContactForm extends AbstractMFNATests {

	public GoToAddContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
    	mfnaPage.goToAddContactForm();
	}
}
