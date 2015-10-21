package com.ps.primerica.tests.qq;

import org.openqa.selenium.WebDriver;

public class FillRequiredFieldsInContactForm extends AbstractQQTests {

	public FillRequiredFieldsInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
    	qqPage.fillRequiredFieldsInContactForm();
	}
}