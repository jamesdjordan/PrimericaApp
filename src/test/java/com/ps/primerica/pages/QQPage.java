package com.ps.primerica.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ps.primerica.components.DateComponent;
import com.ps.primerica.model.Contact;

public class QQPage extends BasePage {
	
	private final String PERSONAL_INFO_URL = "https://test.primericaonline.com/wlpol/apps/services/www/Primerica/desktopbrowser/default/index.html#/quickquote/personal-info";
	private final String PRODUCTS_URL = "https://test.primericaonline.com/wlpol/apps/services/www/Primerica/desktopbrowser/default/index.html#/quickquote/products";
		
	private DateComponent dateComponent;

	private By CLIENT_DOB = By.name("contactDOB");
	private By SPOUSE_DOB = By.name("spouseDOB");

	public QQPage(WebDriver driver) {
		super(driver);
		this.dateComponent = new DateComponent(driver);
	}
	
	public void fillDatesInContactForm(Contact mfnaContact) {
		WebElement clientDOB = driver.findElement(CLIENT_DOB);
		dateComponent.sendDate(clientDOB, mfnaContact.getPersonalInfo().getClient().getDateOfBirth());

		WebElement spouseDOB = driver.findElement(SPOUSE_DOB);
		dateComponent.sendDate(spouseDOB, mfnaContact.getPersonalInfo().getSpouse().getDateOfBirth());
	}
	
	public void goToPersonalInfo() {
		driver.get(PERSONAL_INFO_URL);	
		sleep(6000);
	}

	public void goToProducts() {
		driver.get(PRODUCTS_URL);	
		sleep(6000);
	}

	public void validateDatesInContactForm(Contact mfnaContact) {
		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(CLIENT_DOB));
		WebElement clientDOB = driver.findElement(CLIENT_DOB);
		dateComponent.validateDate("Date of Birth", clientDOB, mfnaContact.getPersonalInfo().getClient().getDateOfBirth());

		WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(SPOUSE_DOB));
		WebElement spouseDOB = driver.findElement(SPOUSE_DOB);
		dateComponent.validateDate("Spouse Date of Birth", spouseDOB, mfnaContact.getPersonalInfo().getSpouse().getDateOfBirth());
	}
	
}
