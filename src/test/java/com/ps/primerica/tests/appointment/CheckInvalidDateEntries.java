package com.ps.primerica.tests.appointment;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ps.primerica.tests.AbstractDateTests;

public class CheckInvalidDateEntries extends AbstractDateTests {

	public CheckInvalidDateEntries(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		try {
			WebElement dateField = driver.findElement(By.name("apptDate"));
			WebElement saveButton = driver.findElement(By.cssSelector("button[title='Save']"));
			
			// Click to initiate field validation
			saveButton.click();			
			//Find the error message, assert is is not hidden
			WebElement error_date = driver.findElement(By.xpath("//*[@id=\"cm-appts-view\"]/form/div/div/div[1]/div[2]/div[1]/div[2]/span[3]"));
			Assert.assertTrue(error_date.isDisplayed());
			Assert.assertEquals("Incorrect validation message!", ERROR_MESSAGE_DATE_REQUIRED, error_date.getText());
			
			Thread.sleep(2000);			
		
			// Populate with invalid date and make sure
			// a) The validation error is shown
			dateField.sendKeys(dateString_invalid_format);
			saveButton.click();
			
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
			
			Thread.sleep(2000);	
			
			dateField.sendKeys(dateString_invalid_range);
			saveButton.click();
			Thread.sleep(6000);	
			error_date = driver.findElement(By.xpath("//*[@id=\"cm-appts-view\"]/form/div/div/div[1]/div[2]/div[1]/div[2]/span[2]"));
			Assert.assertTrue(error_date.isDisplayed());
			Assert.assertEquals("Incorrect validation message!", ERROR_MESSAGE_DATE_RANGE, error_date.getText());
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
