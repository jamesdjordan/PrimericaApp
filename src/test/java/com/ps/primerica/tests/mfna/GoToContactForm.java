package com.ps.primerica.tests.mfna;

import org.openqa.selenium.WebDriver;

public class GoToContactForm extends AbstractMFNATests {

	public GoToContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
    	mfnaPage.goToContactList();
    	
	}
}
