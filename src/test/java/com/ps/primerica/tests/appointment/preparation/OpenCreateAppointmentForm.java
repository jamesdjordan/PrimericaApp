package com.ps.primerica.tests.appointment.preparation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ps.primerica.tests.AbstractBaseTests;

public class OpenCreateAppointmentForm extends AbstractBaseTests {

	public OpenCreateAppointmentForm(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		try {
			driver.findElement(By.cssSelector("#appointments-section > div.row.header-row > div.col-xs-2.right.add-links > a")).click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
