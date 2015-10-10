package com.ps.primerica.pages;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CMPage extends BasePage {

	private By PERSON_LIST = By.id("PersonList");
	private By PERSON_LIST_ITEM = By.cssSelector(".person-list-item.clickable");

	public static final String ADD_CONTACT = "Add Contact";
	public static final String CANCEL = "Cancel";
	public static final String IMPORT_CONTACTS = "Import Contacts";
	public static final String GETTING_STARTED_TUTORIAL = "Getting Started tutorial";

	protected String dateString_slash = "01/03/2016";
	protected String dateString_dash = "01-04-2016";
	protected String dateString_dot = "01.05.2016";
	protected String dateString_invalid_format = "12.12/20a5";
	protected String dateString_invalid_range = "12/25/1989";

	protected final String ERROR_MESSAGE_DATE_REQUIRED = "Date required.";
	protected final String ERROR_MESSAGE_DATE_FORMAT = "Invalid date format.";
	protected final String ERROR_MESSAGE_DATE_RANGE = "Date must be between 01/01/1900 and 12/31/9999.";

	public CMPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void waitForPersonList() {
	    WAIT_60.until(ExpectedConditions.visibilityOfElementLocated(PERSON_LIST));
	}
	
	public void checkPersonList() {
        List<WebElement> items = driver.findElements(PERSON_LIST_ITEM);
        Assert.assertTrue("Person list should not be empty", CollectionUtils.isNotEmpty(items));
	}
	
	public void addContact (String userName) {
		driver.findElement(By.name("firstName")).sendKeys(userName);
		
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		driver.findElement(By.cssSelector("button[title='Save']")).click();
		sleep(3000);
		WebElement nameHeader = driver.findElement(By.cssSelector("#name-section > div.row.no-padding-top > div.col-xs-10 > h3"));
		Assert.assertEquals(userName, nameHeader.getText().trim());
	}
	
	public void loadAppointmentForm() {
		driver.findElement(By.cssSelector("#appointments-section > div.row.header-row > div.col-xs-2.right.add-links > a")).click();
		sleep(5000);
	}
	
	public void checkInvalidDatesInAppointmentForm() {
		WebElement dateField = driver.findElement(By.name("apptDate"));
		WebElement saveButton = driver.findElement(By.cssSelector("button[title='Save']"));
		
		// Click to initiate field validation
		saveButton.click();			
		//Find the error message, assert is is not hidden
		WebElement error_date = driver.findElement(By.xpath("//*[@id=\"cm-appts-view\"]/form/div/div/div[1]/div[2]/div[1]/div[2]/span[3]"));
		Assert.assertTrue(error_date.isDisplayed());
		Assert.assertEquals("Incorrect validation message!", ERROR_MESSAGE_DATE_REQUIRED, error_date.getText());
		
		sleep(2000);			
	
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
		
		sleep(2000);	
		
		dateField.sendKeys(dateString_invalid_range);
		saveButton.click();
		sleep(6000);	
		error_date = driver.findElement(By.xpath("//*[@id=\"cm-appts-view\"]/form/div/div/div[1]/div[2]/div[1]/div[2]/span[2]"));
		Assert.assertTrue(error_date.isDisplayed());
		Assert.assertEquals("Incorrect validation message!", ERROR_MESSAGE_DATE_RANGE, error_date.getText());
	}
}
