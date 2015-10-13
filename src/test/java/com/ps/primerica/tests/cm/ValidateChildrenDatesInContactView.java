package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class ValidateChildrenDatesInContactView extends AbstractCMTests {

	public ValidateChildrenDatesInContactView(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
    	cmPage.validateSpouseDatesInContactView();
 	}
}
