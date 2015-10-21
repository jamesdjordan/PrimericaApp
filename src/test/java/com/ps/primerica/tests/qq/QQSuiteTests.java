package com.ps.primerica.tests.qq;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	GoToPersonalInfo.class,
	FillRequiredFieldsInContactForm.class,
	FillDatesInContactForm.class,
	ValidateDatesInContactForm.class,
})
public class QQSuiteTests {}