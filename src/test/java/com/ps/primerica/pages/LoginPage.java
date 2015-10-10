package com.ps.primerica.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void sendCredentials(String username, String password) {
		driver.get("http://test.primericaonline.com");
		
		// Verify we are on the right page
		Assert.assertEquals("Primerica Online (POL)", driver.getTitle());
		
		driver.findElement(By.name("USER")).sendKeys(username);
		driver.findElement(By.name("PASSWORD")).sendKeys(password);
		driver.findElement(By.name("login_form")).submit();
		sleep(2000);
 	};
}
