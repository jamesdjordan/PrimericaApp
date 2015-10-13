package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class GoToAddContactForm extends AbstractCMTests {

	public GoToAddContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
    	cmPage.goToAddContactForm();
	}
}
