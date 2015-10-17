package com.ps.primerica.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MenuPage extends BasePage {

	private final String CONTEXT_URL = "https://test.primericaonline.com/wlpol/apps/services/www/Primerica/desktopbrowser/default/index.html#/home/content";
	
	private By HAMBURGER = By.id("hamburger");
	
	private By CONTACT_MANAGER_MENU_ITEM = 
			By.cssSelector("[click-track='[\"MenuClick\", \"Menu\", \"Contact Manager\"]']");
		
	private By FINANCIAL_CALCULATORS_MENU_ITEM = 
			By.cssSelector("[click-track=\'[\"MenuClick\", \"Menu\", \"Financial Calculators\"]\']");

	private By MFNA_MENU_ITEM = 
			By.cssSelector("[click-track=\'[\"MenuClick\", \"Menu\", \"Mobile FNA\"]\']");
	
	private By MENU_UTILITIES = By.id("options");
	private By BACKNAV = By.id("backNav");
	private By CHEVRON_LEFT = By.cssSelector(".PRIcons-chevron-left");
	private By RIGHT_SLIDE_MENU_CONTAINER = By.id("rightSlideMenuContainer");

	public MenuPage(WebDriver driver) {
		super(driver);
	}

	public void gotoHomeContext() {
		driver.get(CONTEXT_URL);	
		sleep(5000);
		Assert.assertEquals("Primerica", driver.getTitle());
	}
	
	public void waitForHamburgerMenu() {
        WAIT_60.until(ExpectedConditions.elementToBeClickable(HAMBURGER));
	};
	
	public void waitAndClickOnHamburger() {
		waitForHamburgerMenu();
        sleep(2000);
        driver.findElement(HAMBURGER).click();
        sleep(2500);
	}
	
	public void clickOnContactManager() {
		WAIT_60.until(ExpectedConditions.elementToBeClickable(CONTACT_MANAGER_MENU_ITEM));
        driver.findElement(CONTACT_MANAGER_MENU_ITEM).click();
        sleep(5000);
	}
	
	public void clickOnItemInContextMenu(String label) {
		WAIT_10.until(ExpectedConditions.elementToBeClickable(MENU_UTILITIES));
	    driver.findElement(MENU_UTILITIES).click();
	    WAIT_10.until(ExpectedConditions.visibilityOfElementLocated(RIGHT_SLIDE_MENU_CONTAINER));
	    findTextAndClickByType("span", label);
	}
	
	public void findTextAndClickByType (String type, String label) {
//		By element = By.xpath("//" + type + "[contains(text(),\"" + label +"\")]");
		By element = By.xpath("//" + type + "[text()[contains(.,\"" + label +"\")]]");
		WAIT_10.until(ExpectedConditions.elementToBeClickable(element));
		driver.findElement(element).click();
		sleep(2000);
	}
	
	public void backByScript() {
    	((RemoteWebDriver)driver).executeScript("window.history.go(-1)");
    	sleep(2000);
	}
	
	public void back() {
		driver.navigate().back();
    	sleep(2000);
	}
	
	public void clickOnBack() {
		sleep(2000);
		WAIT_10.until(ExpectedConditions.elementToBeClickable(BACKNAV));
		driver.findElement(BACKNAV).click();
	}
}
