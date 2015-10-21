package com.ps.primerica.tests.mfna;

import org.openqa.selenium.WebDriver;

import com.ps.primerica.pages.MFNAPage;
import com.ps.primerica.pages.MenuPage;
import com.ps.primerica.tests.AbstractBaseTests;

public abstract class AbstractMFNATests extends AbstractBaseTests {

    protected MFNAPage mfnaPage;
    protected MenuPage menuPage;
	
	public AbstractMFNATests(WebDriver driver) {
		super(driver);
		mfnaPage = new MFNAPage(driver);
		menuPage = new MenuPage(driver);
	}

}