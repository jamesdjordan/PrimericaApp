package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class OpenAppointmentForm extends AbstractCMTests {

	public OpenAppointmentForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		cmPage.loadAppointmentForm();
	}
}
