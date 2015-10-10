package com.ps.primerica.tests.cm;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CheckValidDatesInAppointmentForm extends AbstractCMTests {

	public CheckValidDatesInAppointmentForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		WebElement locationElement = driver.findElement(By.name("location"));
		locationElement.sendKeys("Test Location");			
		Select appointmentTypes = new Select(driver.findElement(By.cssSelector("select[name='apptType']")));
		appointmentTypes.selectByIndex(1);

		WebElement dateField = driver.findElement(By.name("apptDate"));
		WebElement saveButton = driver.findElement(By.cssSelector("button[title='Save']"));
		WebElement errorLabel = driver.findElement(By.cssSelector("[ng-show=\"(validationMode || apptForm.apptDate.$dirty) && apptForm.apptDate.$invalid\"]"));

		dateFormPage.checkValid(dateField, saveButton, errorLabel);
		
		// We should be back to the contact list window
		// for now just assert the top navigation element. In future check to make sure the amount of appointments got increased by one
		WebElement navElement = driver.findElement(By.xpath("//*[@id=\"topNav\"]/div/div[3]/span"));
		Assert.assertNotNull(navElement);
		Assert.assertEquals("Contact Profile", navElement.getText());
		// also assert we are back on the profile
		Assert.assertEquals(userName, driver.findElement(By.cssSelector("#name-section > div.row.no-padding-top > div.col-xs-10 > h3")).getText());
		// TODO: Assert the number of appointments is 1
	}

}

