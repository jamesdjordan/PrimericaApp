package com.ps.primerica.tests.cm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckInvalidDatesInAppointmentForm extends AbstractCMTests {

	public CheckInvalidDatesInAppointmentForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		WebElement dateField = driver.findElement(By.name("apptDate"));
		WebElement saveButton = driver.findElement(By.cssSelector("button[title='Save']"));
		WebElement errorLabel = driver.findElement(By.cssSelector("[ng-show=\"(validationMode || apptForm.apptDate.$dirty) && apptForm.apptDate.$invalid\"]"));
		dateFormPage.checkInvalid(dateField, saveButton, errorLabel);
	}

}
