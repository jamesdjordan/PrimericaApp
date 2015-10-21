package com.ps.primerica.tests.mfna;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	GoToAddContactForm.class,
	SelectClientLocation.class,
	FillRequiredFieldsInContactForm.class,
	FillDatesInContactForm.class,
	GoToContactForm.class,
	ValidateDatesInContactForm.class,
})
public class MFNASuiteTests {}
