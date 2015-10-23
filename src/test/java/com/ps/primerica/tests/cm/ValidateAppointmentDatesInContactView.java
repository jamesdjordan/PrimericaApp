package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class ValidateAppointmentDatesInContactView extends AbstractCMTests {

	public ValidateAppointmentDatesInContactView(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
    	cmPage.validateAppointmentDatesInContactView(contact);
 	}
}
