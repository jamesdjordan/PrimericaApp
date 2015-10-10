package com.ps.primerica.tests.cm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CheckPersonList.class,
	AddContact.class,
	OpenAppointmentForm.class,
	CheckInvalidDatesInAppointmentForm.class,
	CheckValidDatesInAppointmentForm.class
})
public class CMSuiteTests {}
