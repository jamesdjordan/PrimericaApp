package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class FillSpouseDatesInContactForm extends AbstractCMTests {

	public FillSpouseDatesInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		driver.navigate().back();
    	menuPage.findTextAndClickByType("a", "Add Spouse");
    	cmPage.addRequiredSpouseContactFormValues(userName);
    	cmPage.fillDateFieldsInSpouseContactForm();
    	cmPage.saveForm();
	}
}