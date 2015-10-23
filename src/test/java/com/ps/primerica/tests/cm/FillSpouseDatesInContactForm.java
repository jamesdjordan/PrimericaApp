package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class FillSpouseDatesInContactForm extends AbstractCMTests {

	public FillSpouseDatesInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
    	cmPage.goToContactProfile();
    	menuPage.findTextAndClickByType("a", "Add Spouse");
    	cmPage.addRequiredSpouseContactFormValues(contact);
    	cmPage.fillDateFieldsInSpouseContactForm(contact);
    	cmPage.saveForm();
	}
}