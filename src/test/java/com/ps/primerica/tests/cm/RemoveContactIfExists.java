package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

public class RemoveContactIfExists extends AbstractCMTests {

	public RemoveContactIfExists(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		boolean exists;
		cmPage.goToContactList();
		cmPage.searchContact(contact);
		while (exists = cmPage.selectContactIfExists(contact)) {
			menuPage.clickOnItemInContextMenu("Delete Contact");
			cmPage.clickOnPositiveButtonInModal();
		}
 	}
}
