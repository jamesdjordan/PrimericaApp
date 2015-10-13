package com.ps.primerica.components;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.ps.primerica.pages.BasePage;

public class DateComponent extends BasePage {

	protected String DATE_STRING_SLASH = "01/03/2016";
	protected String DATE_STRING_DASH = "01-04-2016";
	protected String DATE_STRING_DOT = "01.05.2016";
	protected String DATE_STRING_INVALID_FORMAT = "12.12/20a5";
	protected String DATE_STRING_INVALID_RANGE = "12/25/1989";

	protected final String ERROR_MESSAGE_DATE_REQUIRED = "Date required.";
	protected final String ERROR_MESSAGE_DATE_FORMAT = "Invalid date format.";
//	protected final String ERROR_MESSAGE_DATE_RANGE = "Date must be between 01/01/1900 and 12/31/9999.";
	protected final String ERROR_MESSAGE_DATE_RANGE = "Date must be between 01/01/1900 and 10/12/1997.";
	
	private static final String DELIMETER = "/";	

	public DateComponent(WebDriver driver) {
		super(driver);
	}

	
	private void sendValues(List<WebElement> dateInnerFields, String[] values) {
		for (WebElement dateInnerField : dateInnerFields) {
			int index = dateInnerFields.indexOf(dateInnerField);
			dateInnerField.sendKeys(values[index]);
		}
	}
	
	private String getText(List<WebElement> dateInnerFields) {
		StringBuilder sb = new StringBuilder();
		for (WebElement dateInnerField : dateInnerFields) {
			sb.append(new Select(dateInnerField).getFirstSelectedOption().getAttribute("text"));
			int index = dateInnerFields.indexOf(dateInnerField);
			if (index < dateInnerFields.size() - 1) {
				sb.append(DELIMETER);
			}
		}
		return sb.toString();
		
	}
	
	public void sendDate(WebElement dateField, String date) {
		String[] dateAr = date.split(DELIMETER);
		List<WebElement> dateInnerFields = dateField.findElements(By.tagName("select"));
		sendValues(dateInnerFields, dateAr);
	}

	public void validateDate(String message, WebElement dateField, String date) {
		List<WebElement> dateInnerFields = dateField.findElements(By.tagName("select"));
		String actual = getText(dateInnerFields);
		Assert.assertEquals(message, date, actual);
	}

	public void validateDateInDiv(String message, WebElement dateField, String date) {
		List<WebElement> dateInnerDivs = dateField.findElements(By.tagName("div"));
		Assert.assertEquals(message, date, dateInnerDivs.get(1).getAttribute("innerText"));
	}

	public void validateDateInElement(WebElement dateField, String date, String element, int index ) {
		List<WebElement> dateInnerDivs = dateField.findElements(By.tagName(element));
		Assert.assertEquals(date, dateInnerDivs.get(index).getAttribute("innerText"));
	}

	public void checkInvalid(WebElement dateField, WebElement saveButton, WebElement errorLabel) {
		String dateRangeMin = dateField.getAttribute("date-range-min");
		String[] dateRangeMinArray = dateRangeMin.split(DELIMETER);
		String dateRangeMax = dateField.getAttribute("date-range-max");
		String[] dateRangeMaxArray = dateRangeMin.split(DELIMETER);
		
		List<WebElement> dateInnerFields = dateField.findElements(By.tagName("select"));
		
		// Check min range value
		sendValues(dateInnerFields, dateRangeMinArray);
		String actual = getText(dateInnerFields);
//		Assert.assertEquals(dateRangeMin, actual);

		// Check max range value
		sendValues(dateInnerFields, dateRangeMaxArray);
		actual = getText(dateInnerFields);
	//	Assert.assertEquals(dateRangeMax, actual);

/*		for (WebElement dateInnerField : dateInnerFields) {
			dateInnerField.sendKeys("01");
		}
*/
		/*
		// Click to initiate field validation
		saveButton.click();			
		
		Assert.assertTrue(errorLabel.isDisplayed());
		Assert.assertEquals("Incorrect validation message!", ERROR_MESSAGE_DATE_REQUIRED, errorLabel.getText());

		sleep(2000);			
	
		// Populate with invalid date and make sure
		// a) The validation error is shown
		dateField.sendKeys(DATE_STRING_INVALID_FORMAT);
		saveButton.click();
		*/
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
		/*
		Assert.assertTrue(errorLabel.isDisplayed());
		Assert.assertEquals("Incorrect validation message!", ERROR_MESSAGE_DATE_REQUIRED, errorLabel.getText());

		sleep(2000);	
		
		dateField.sendKeys(DATE_STRING_INVALID_RANGE);
		saveButton.click();
		
		sleep(2000);
		
		Assert.assertTrue(errorLabel.isDisplayed());
		Assert.assertEquals("Incorrect validation message!", ERROR_MESSAGE_DATE_REQUIRED, errorLabel.getText());
*/
	}
	
	public void checkValid(WebElement dateField, WebElement saveButton, WebElement errorLabel) {
		dateField.sendKeys(DATE_STRING_SLASH);
		saveButton.click();
		sleep(10000);
		Assert.assertFalse(errorLabel.isDisplayed());
	}
}
