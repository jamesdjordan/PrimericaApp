package com.tests.selenium;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class Test_Primerica_Dates {

	private WebDriver driver;
	
	private String dateString_slash = "11/12/2015";
	private String dateString_dash = "11-13-2015";
	private String dateString_dot = "11.14.2015";
	private String dateString_invalid_format = "12.12/20a5";
	private String dateString_invalid_range = "12/25/1989";

	private final String ERROR_MESSAGE_DATE_REQUIRED = "Date required.";
	private final String ERROR_MESSAGE_DATE_FORMAT = "Invalid date format.";
	private final String ERROR_MESSAGE_DATE_RANGE = "Date must be between 01/01/1900 and 12/31/9999.";
	
	@Before
	public void setup_local(){
		System.setProperty("webdriver.chrome.driver", "C:\\SWTOOLS\\Selenium\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();		
		//driver = new FirefoxDriver();
		
		openCMCreateApointmentPage();
	}
	
	//@Before
	public void setup_remote(){
		DesiredCapabilities caps = null;

/*		
 		// Stock browser on Android
 		caps = DesiredCapabilities.android();
		caps.setCapability("appiumVersion", "1.4.11");
		caps.setCapability("deviceName","Android Emulator");
		caps.setCapability("deviceOrientation", "portrait");
		caps.setCapability("browserName", "Browser");
		caps.setCapability("platformVersion", "5.1");
		caps.setCapability("platformName","Android");*/
		
/*		
 		// Safari on Mac
 		caps = DesiredCapabilities.safari();
		caps.setCapability("platform", "OS X 10.11");
		caps.setCapability("version", "8.1");
*/
		
/*		// iPhone 6 Plus with iOS 9.0		
		caps = DesiredCapabilities.iphone();
		caps.setCapability("platform", "OS X 10.10");
		caps.setCapability("version", "9.0");
		caps.setCapability("deviceName","iPhone 6 Plus");
		caps.setCapability("deviceOrientation", "portrait");*/
		
		String sauceUrl = String.format(
				"http://%s:%s@ondemand.saucelabs.com:80/wd/hub", 
				"aramhovhannisyan", 
				"ff0523ef-c388-4295-bc36-528ca6163f28");
		
		try {
			driver = new RemoteWebDriver(new URL(sauceUrl), caps);			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void openCMCreateApointmentPage(){
		
		try{
			driver.get("http://test.primericaonline.com");
			
			// Verify we are on the right page
			Assert.assertEquals("Primerica Online (POL)", driver.getTitle());
			
			driver.findElement(By.name("USER")).sendKeys("EUPS1");
			driver.findElement(By.name("PASSWORD")).sendKeys("oct2015");
			driver.findElement(By.name("login_form")).submit();
					
			Thread.sleep(2000);
			// TODO: need to check some form elements to make sure we have logged in
			
	//		driver.get("https://test.primericaonline.com/wlpol/apps/services/www/Primerica/desktopbrowser/default/index.html#/home/content");
	//
	//		Thread.sleep(2000);
	//		Assert.assertEquals("Primerica", driver.getTitle());
			
			driver.get("https://test.primericaonline.com/wlpol/apps/services/www/Primerica/desktopbrowser/default/index.html#/cm/contacts/EUPS1/6552928/addAppt");
			Thread.sleep(5000);			
			Select appointmentTypes = new Select(driver.findElement(By.cssSelector("select[name='apptType']")));
			appointmentTypes.selectByIndex(1);
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testInvalidDateEntries(){
		try {
			
			WebElement dateField = driver.findElement(By.name("apptDate"));
			WebElement saveButton = driver.findElement(By.cssSelector("button[title='Save']"));
			
			// Click to initiate field validation
			saveButton.click();			
			//Find the error message, assert is is not hidden
			WebElement error_date = driver.findElement(By.xpath("//*[@id=\"cm-appts-view\"]/form/div/div/div[1]/div[2]/div[1]/div[2]/span[3]"));
			Assert.assertTrue(error_date.isDisplayed());
			Assert.assertEquals("Incorrect validation message!", ERROR_MESSAGE_DATE_REQUIRED, error_date.getText());
			
			Thread.sleep(5000);			
		
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
			
			Thread.sleep(5000);	
			
			dateField.sendKeys(dateString_invalid_range);
			saveButton.click();
			error_date = driver.findElement(By.xpath("//*[@id=\"cm-appts-view\"]/form/div/div/div[1]/div[2]/div[1]/div[2]/span[1]"));
			Assert.assertTrue(error_date.isDisplayed());
			Assert.assertEquals("Incorrect validation message!", ERROR_MESSAGE_DATE_RANGE, error_date.getText());
						
			Thread.sleep(5000);
			
			dateField.sendKeys(dateString_slash);
			saveButton.click();
			
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testValidDateEntries(){
		try{
			WebElement dateField = driver.findElement(By.name("apptDate"));
			WebElement saveButton = driver.findElement(By.cssSelector("button[title='Save']"));
			
			dateField.sendKeys(dateString_slash);
			saveButton.click();
			
			Thread.sleep(3000);
			
			// We should be back to the contact list window
			// for now just assert the top navigation element. In future check to make sure the amount of appointments got increased by one
			WebElement navElement = driver.findElement(By.xpath("//*[@id=\"topNav\"]/div/div[3]/span"));
			Assert.assertNotNull(navElement);
			Assert.assertEquals("Contact Profile", navElement.getText());
			
			
		}catch(InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@After
	public void tearDown(){
		driver.quit();
	}
	
}