package com.ps.primerica.tests.cm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	RemoveContactIfExists.class,
	GoToAddContactForm.class,
	FillDatesInContactForm.class,
	ValidateDatesInContactView.class,
	ValidateDatesInContactForm.class,
	FillFutureDateInLastContactAndValidate.class,
	FillChildrenDatesInContactForm.class,
	ValidateChildrenDatesInContactView.class,
	ValidateChildrenDatesInContactForm.class,
	FillSpouseDatesInContactForm.class,
	ValidateSpouseDatesInContactView.class,
	ValidateSpouseDatesInContactForm.class,
	FillAppointmentDatesInContactForm.class,
	ValidateAppointmentDatesInContactView.class,
	ValidateAppointmentDatesInContactForm.class
})
public class CMSuiteTests {}
