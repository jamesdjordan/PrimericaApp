package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class FillAppointmentDatesInContactForm extends AbstractCMTests {

	public FillAppointmentDatesInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		driver.navigate().back();
		driver.navigate().back();
		menuPage.findTextAndClickByType("a", "Add Appointment");
    	cmPage.addRequiredAppointmentContactFormValues();
    	cmPage.fillDateFieldsInAppointmentContactForm();
    	cmPage.saveForm();
	}
}
