package com.ps.primerica.tests.login;

import org.openqa.selenium.WebDriver;

public class SendValidCredentials extends AbstractLoginTests {

	public SendValidCredentials(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		loginPage.sendCredentials(USERNAME, PASSWORD);
	}

}
