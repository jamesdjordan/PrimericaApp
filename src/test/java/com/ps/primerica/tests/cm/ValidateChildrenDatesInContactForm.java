package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

import com.ps.primerica.model.Child;

public class ValidateChildrenDatesInContactForm extends AbstractCMTests {

	public ValidateChildrenDatesInContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		Child child = contact.getChildren().get(0);
		menuPage.findTextAndClickByType("span", child.getFirstName());
    	cmPage.validateChildrenDatesInContactForm(contact);
 	}
}
