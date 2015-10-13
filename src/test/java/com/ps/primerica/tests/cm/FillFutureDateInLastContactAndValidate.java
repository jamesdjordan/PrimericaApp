package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class FillFutureDateInLastContactAndValidate extends AbstractCMTests {

	public FillFutureDateInLastContactAndValidate(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		cmPage.fillFutureDateInLastContactAndValidate();
	}
}

