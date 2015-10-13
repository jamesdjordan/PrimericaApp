package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class ValidateChildrenDatesInContactForm extends AbstractCMTests {

	public ValidateChildrenDatesInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		menuPage.findTextAndClickByType("span", "userName");
    	cmPage.validateChildrenDatesInContactForm();
 	}
}
