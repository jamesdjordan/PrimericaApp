package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class FillAppointmentDatesInContactForm extends AbstractCMTests {

	public FillAppointmentDatesInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		menuPage.backByScript();
		menuPage.clickOnBack();
		menuPage.clickOnItemInContextMenu("Add Appointment");
    	cmPage.addRequiredAppointmentContactFormValues(contact);
    	cmPage.fillDateFieldsInAppointmentContactForm(contact);
    	cmPage.saveForm();
	}
}
