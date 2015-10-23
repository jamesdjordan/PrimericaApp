package com.ps.primerica.tests.qq;

import org.openqa.selenium.WebDriver;

import com.ps.primerica.model.Contact;
import com.ps.primerica.pages.MenuPage;
import com.ps.primerica.pages.QQPage;
import com.ps.primerica.tests.AbstractBaseTests;

public abstract class AbstractQQTests extends AbstractBaseTests {

	private final static String QQ_CONTACT_PARH = "qq/contact.json";

	protected Contact contact = mapper.getObjectFromJson(QQ_CONTACT_PARH, Contact.class);

    protected QQPage qqPage;
    protected MenuPage menuPage;
	
	public AbstractQQTests(WebDriver driver) {
		super(driver);
		qqPage = new QQPage(driver);
		menuPage = new MenuPage(driver);
	}
}