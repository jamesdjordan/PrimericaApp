package com.ps.primerica.tests.qq;

import org.openqa.selenium.WebDriver;

public class GoToPersonalInfo extends AbstractQQTests {

	public GoToPersonalInfo(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
    	qqPage.goToPersonalInfo();
	}
}

