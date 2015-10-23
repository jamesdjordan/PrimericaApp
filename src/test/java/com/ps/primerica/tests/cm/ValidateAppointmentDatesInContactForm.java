package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class ValidateAppointmentDatesInContactForm extends AbstractCMTests {

	public ValidateAppointmentDatesInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		cmPage.goToEditAppointment();
    	cmPage.validateAppointmentDatesInContactForm(contact);
 	}
}
