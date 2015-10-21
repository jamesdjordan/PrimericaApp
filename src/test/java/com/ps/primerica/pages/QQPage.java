package com.ps.primerica.pages;

import org.openqa.selenium.WebDriver;

public class QQPage extends BasePage {
	
	private final String PERSONAL_INFO_URL = "https://test.primericaonline.com/wlpol/apps/services/www/Primerica/desktopbrowser/default/index.html#/quickquote/personal-info";

	public QQPage(WebDriver driver) {
		super(driver);
	}

	public void fillRequiredFieldsInContactForm() {
		
	}
	
	public void fillDatesInContactForm() {
		
	}
	
	public void goToPersonalInfo() {
		driver.get(PERSONAL_INFO_URL);	
		sleep(2000);
	}
	
	public void validateDatesInContactForm() {
		
	}
}
