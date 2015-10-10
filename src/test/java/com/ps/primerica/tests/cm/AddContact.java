package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

import com.ps.primerica.pages.CMPage;

public class AddContact extends AbstractCMTests {

	public AddContact(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
    	menuPage.clickOnItemInContextMenu(CMPage.ADD_CONTACT);
    	cmPage.addContact(userName);
 	}
}
