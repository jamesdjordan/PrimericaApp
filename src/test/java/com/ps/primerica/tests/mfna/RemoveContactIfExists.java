package com.ps.primerica.tests.mfna;

import org.openqa.selenium.WebDriver;

public class RemoveContactIfExists extends AbstractMFNATests {

	public RemoveContactIfExists(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		mfnaPage.removeContactIfExists(contact);
 	}
}
