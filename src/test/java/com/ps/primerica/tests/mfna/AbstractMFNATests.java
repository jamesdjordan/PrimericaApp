package com.ps.primerica.tests.mfna;

import org.openqa.selenium.WebDriver;

import com.ps.primerica.model.Contact;
import com.ps.primerica.pages.MFNAPage;
import com.ps.primerica.pages.MenuPage;
import com.ps.primerica.tests.AbstractBaseTests;

public abstract class AbstractMFNATests extends AbstractBaseTests {
	
	private final static String MFNA_CONTACT_PARH = "mfna/contact.json";

	protected Contact contact = mapper.getObjectFromJson(MFNA_CONTACT_PARH, Contact.class);

    protected MFNAPage mfnaPage;
    protected MenuPage menuPage;
	
	public AbstractMFNATests(WebDriver driver) {
		super(driver);
		mfnaPage = new MFNAPage(driver);
		menuPage = new MenuPage(driver);
	}

}