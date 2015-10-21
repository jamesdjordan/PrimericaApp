package com.ps.primerica.tests.qq;

import org.openqa.selenium.WebDriver;

import com.ps.primerica.pages.MenuPage;
import com.ps.primerica.pages.QQPage;
import com.ps.primerica.tests.AbstractBaseTests;

public abstract class AbstractQQTests extends AbstractBaseTests {

    protected QQPage qqPage;
    protected MenuPage menuPage;
	
	public AbstractQQTests(WebDriver driver) {
		super(driver);
		qqPage = new QQPage(driver);
		menuPage = new MenuPage(driver);
	}
}