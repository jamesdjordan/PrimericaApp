package com.ps.primerica.pages;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePage {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

	protected WebDriver driver;
	
	// Waits
	protected WebDriverWait WAIT_120;
	protected WebDriverWait WAIT_60;
	protected WebDriverWait WAIT_30;
	protected WebDriverWait WAIT_10;
	protected WebDriverWait WAIT_5;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		WAIT_120 = new WebDriverWait(driver, 120);
		WAIT_60 = new WebDriverWait(driver, 60);
		WAIT_30 = new WebDriverWait(driver, 30);
		WAIT_10 = new WebDriverWait(driver, 10);
		WAIT_5 = new WebDriverWait(driver, 5);
	}
	
	protected void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	protected void hideKeyboard() {
		if (driver instanceof AndroidDriver) {
			try {
			((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {}
		}
	}
}
