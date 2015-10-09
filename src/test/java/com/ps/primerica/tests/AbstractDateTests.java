package com.ps.primerica.tests;

import org.openqa.selenium.WebDriver;

public abstract class AbstractDateTests extends AbstractBaseTests {
	
	protected String dateString_slash = "01/03/2016";
	protected String dateString_dash = "01-04-2016";
	protected String dateString_dot = "01.05.2016";
	protected String dateString_invalid_format = "12.12/20a5";
	protected String dateString_invalid_range = "12/25/1989";

	protected final String ERROR_MESSAGE_DATE_REQUIRED = "Date required.";
	protected final String ERROR_MESSAGE_DATE_FORMAT = "Invalid date format.";
	protected final String ERROR_MESSAGE_DATE_RANGE = "Date must be between 01/01/1900 and 12/31/9999.";

	public AbstractDateTests(WebDriver driver) {
		super(driver);
	}

}
