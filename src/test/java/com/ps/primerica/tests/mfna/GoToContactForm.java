package com.ps.primerica.tests.mfna;

import org.openqa.selenium.WebDriver;

public class GoToContactForm extends AbstractMFNATests {

	public GoToContactForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		String firstName = contact.getPersonalInfo().getClient().getFirstName();
		String lastName = contact.getPersonalInfo().getClient().getLastName();
    	mfnaPage.goToContactList();
    	mfnaPage.searchContact(firstName + " " + lastName);
    	mfnaPage.goToContactForm(contact);
 	}
}
