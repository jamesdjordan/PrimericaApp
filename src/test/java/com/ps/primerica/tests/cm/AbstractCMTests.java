package com.ps.primerica.tests.cm;

import org.openqa.selenium.WebDriver;

import com.ps.primerica.model.Contact;
import com.ps.primerica.pages.CMPage;
import com.ps.primerica.pages.MenuPage;
import com.ps.primerica.tests.AbstractBaseTests;

public abstract class AbstractCMTests extends AbstractBaseTests {

	private final static String CM_CONTACT_PARH = "cm/contact.json";

	protected Contact contact = mapper.getObjectFromJson(CM_CONTACT_PARH, Contact.class);

    protected CMPage cmPage;
    protected MenuPage menuPage;
	protected String userName = "ATestUser";
	
	public AbstractCMTests(WebDriver driver) {
		super(driver);
		cmPage = new CMPage(driver);
		menuPage = new MenuPage(driver);
	}
}
