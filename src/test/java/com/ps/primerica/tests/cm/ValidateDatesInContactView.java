package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class ValidateDatesInContactView extends AbstractCMTests {

	public ValidateDatesInContactView(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
    	cmPage.validateDatesInContactView();
 	}
}
