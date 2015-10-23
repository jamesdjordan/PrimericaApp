package com.ps.primerica.tests.mfna;

import org.openqa.selenium.WebDriver;

public class SelectClientLocation extends AbstractMFNATests {

	public SelectClientLocation(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		mfnaPage.selectClientLocation(contact);
	}
}
