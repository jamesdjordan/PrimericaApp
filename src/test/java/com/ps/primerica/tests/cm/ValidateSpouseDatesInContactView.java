package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class ValidateSpouseDatesInContactView extends AbstractCMTests {

	public ValidateSpouseDatesInContactView(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
    	cmPage.validateSpouseDatesInContactView(contact);
 	}
}
