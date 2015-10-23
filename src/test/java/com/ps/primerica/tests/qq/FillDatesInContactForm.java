package com.ps.primerica.tests.qq;

import org.openqa.selenium.WebDriver;

public class FillDatesInContactForm  extends AbstractQQTests {

	public FillDatesInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
    	qqPage.fillDatesInContactForm(contact);
    	qqPage.goToProducts();
    	qqPage.goToPersonalInfo();
	}
}
