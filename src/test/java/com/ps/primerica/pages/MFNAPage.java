package com.ps.primerica.pages;

import org.openqa.selenium.WebDriver;

public class MFNAPage extends BasePage {

	private final String ADD_CONTACT_FORM_URL = "https://test.primericaonline.com/wlpol/apps/services/www/Primerica/desktopbrowser/default/index.html#/fna/detail/addContact/privacy-notice";
	private final String CONTACT_LIST_URL = "https://test.primericaonline.com/wlpol/apps/services/www/Primerica/desktopbrowser/default/index.html#/fna/contacts";

	public MFNAPage(WebDriver driver) {
		super(driver);
	}
	
	public void goToAddContactForm() {
		driver.get(ADD_CONTACT_FORM_URL);	
		sleep(2000);

	}

	public void goToContactList() {
		driver.get(CONTACT_LIST_URL);	
		sleep(2000);

	}

	public void goToContactForm() {
		driver.get(CONTACT_LIST_URL);	
		sleep(2000);

	}

	public void selectClientLocation() {
		
	}

	public void fillRequiredFieldsInContactForm() {
		
	}

	public void fillDatesInContactForm() {
		
	}

	public void validateDatesInContactForm() {
		
	}
}
