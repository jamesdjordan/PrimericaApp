package com.ps.primerica.pages;

import io.appium.java_client.android.AndroidDriver;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

	private String SWITCH_TO_DESKTOP = "Switch to Desktop View";
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void sendCredentials(String username, String password) {
		driver.get("http://test.primericaonline.com");
		sleep(3000);

		if (driver instanceof AndroidDriver) {
			By element = By.xpath("//a[text()[contains(.,\"" + SWITCH_TO_DESKTOP +"\")]]");
			WebElement switchTo = driver.findElement(element);
			switchTo.click();
			sleep(2000);
		}

		// Verify we are on the right page
		Assert.assertEquals("Primerica Online (POL)", driver.getTitle());
		
		driver.findElement(By.name("USER")).sendKeys(username);
		driver.findElement(By.name("PASSWORD")).sendKeys(password);
		driver.findElement(By.name("login_form")).submit();
		sleep(2000);
 	};
}
