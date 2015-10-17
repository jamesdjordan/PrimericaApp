package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class FillChildrenDatesInContactForm extends AbstractCMTests {

	public FillChildrenDatesInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		menuPage.back();
		menuPage.findTextAndClickByType("a", " Add Children");
    	cmPage.addRequiredChildrenContactFormValues(userName);
    	cmPage.fillDateFieldsInChildrenContactForm();
    	cmPage.saveForm();
	}
}
