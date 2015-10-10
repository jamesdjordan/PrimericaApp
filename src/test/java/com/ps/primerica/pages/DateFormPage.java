package com.ps.primerica.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class DateFormPage extends BasePage {

	protected String DATE_STRING_SLASH = "01/03/2016";
	protected String DATE_STRING_DASH = "01-04-2016";
	protected String DATE_STRING_DOT = "01.05.2016";
	protected String DATE_STRING_INVALID_FORMAT = "12.12/20a5";
	protected String DATE_STRING_INVALID_RANGE = "12/25/1989";

	protected final String ERROR_MESSAGE_DATE_REQUIRED = "Date required.";
	protected final String ERROR_MESSAGE_DATE_FORMAT = "Invalid date format.";
	protected final String ERROR_MESSAGE_DATE_RANGE = "Date must be between 01/01/1900 and 12/31/9999.";

	public DateFormPage(WebDriver driver) {
		super(driver);
	}

	public void checkInvalid(WebElement dateField, WebElement saveButton, WebElement errorLabel) {
				
		// Click to initiate field validation
		saveButton.click();			
		
		Assert.assertTrue(errorLabel.isDisplayed());
		Assert.assertEquals("Incorrect validation message!", ERROR_MESSAGE_DATE_REQUIRED, errorLabel.getText());

		sleep(2000);			
	
		// Populate with invalid date and make sure
		// a) The validation error is shown
		dateField.sendKeys(DATE_STRING_INVALID_FORMAT);
		saveButton.click();
		
		/*
		if(driver instanceof FirefoxDriver){
			// Invalid format clears up on FireFox, so the error message is still 'Date required'
			error_date = driver.findElement(By.xpath("//*[@id=\"cm-appts-view\"]/form/div/div/div[1]/div[2]/div[1]/div[2]/span[3]"));
			Assert.assertTrue(error_date.isDisplayed());
			Assert.assertEquals("Incorrect validation message!", ERROR_MESSAGE_DATE_REQUIRED, error_date.getText());
		}else if(driver instanceof ChromeDriver){
			// On Chrome the invalid date is being converted to a valid date
			error_date = driver.findElement(By.xpath("//*[@id=\"cm-appts-view\"]/form/div/div/div[1]/div[2]/div[1]/div[2]/span[1]"));
			Assert.assertTrue(error_date.isDisplayed());
			Assert.assertEquals("Incorrect validation message!", ERROR_MESSAGE_DATE_RANGE, error_date.getText());
		}
		*/
		
		Assert.assertTrue(errorLabel.isDisplayed());
		Assert.assertEquals("Incorrect validation message!", ERROR_MESSAGE_DATE_REQUIRED, errorLabel.getText());

		sleep(2000);	
		
		dateField.sendKeys(DATE_STRING_INVALID_RANGE);
		saveButton.click();
		
		sleep(2000);
		
		Assert.assertTrue(errorLabel.isDisplayed());
		Assert.assertEquals("Incorrect validation message!", ERROR_MESSAGE_DATE_REQUIRED, errorLabel.getText());
	}
	
	public void checkValid(WebElement dateField, WebElement saveButton, WebElement errorLabel) {
		dateField.sendKeys(DATE_STRING_SLASH);
		saveButton.click();
		sleep(10000);
		Assert.assertFalse(errorLabel.isDisplayed());
	}
}
