package com.ps.primerica.tests.appointment.preparation;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ps.primerica.tests.AbstractBaseTests;

public class CreateNewContactRecord extends AbstractBaseTests {

	
	public CreateNewContactRecord(WebDriver driver) {
		super(driver);
	}

	@Override
	public void executeTest() {
		try{
			// generate unique user name
			//String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			System.out.println("Generating contact record for ".concat(userName));
			driver.get("http://test.primericaonline.com");
			
			// Verify we are on the right page
			Assert.assertEquals("Primerica Online (POL)", driver.getTitle());
			
			driver.findElement(By.name("USER")).sendKeys("EUPS1");
			driver.findElement(By.name("PASSWORD")).sendKeys("oct2015");
			driver.findElement(By.name("login_form")).submit();
					
			Thread.sleep(2000);
			// TODO: need to check some form elements to make sure we have logged in
			
			driver.get("https://test.primericaonline.com/wlpol/apps/services/www/Primerica/desktopbrowser/default/index.html#/home/content");	
			Thread.sleep(5000);
			Assert.assertEquals("Primerica", driver.getTitle());
			
			driver.findElement(By.id("hamburger")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"leftSlideMenuContainer\"]/nav/div[2]/div[2]")).click();
			
			Thread.sleep(3000);
			
			driver.findElement(By.id("options")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"rightSlideMenuContainer\"]/nav/div[2]")).click();
			Thread.sleep(2000);
			driver.findElement(By.name("firstName")).sendKeys(userName);
			
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			
			driver.findElement(By.cssSelector("button[title='Save']")).click();
			Thread.sleep(3000);
			//WebElement nameHeader = driver.findElement(By.cssSelector("//*[@id=\"name-section\"]/div[1]/div[1]/h3"));
			WebElement nameHeader = driver.findElement(By.cssSelector("#name-section > div.row.no-padding-top > div.col-xs-10 > h3"));
			Assert.assertEquals(userName, nameHeader.getText().trim());
			
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
