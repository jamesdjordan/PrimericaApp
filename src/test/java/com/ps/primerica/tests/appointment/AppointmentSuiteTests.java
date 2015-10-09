package com.ps.primerica.tests.appointment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ps.primerica.tests.appointment.preparation.CreateNewContactRecord;
import com.ps.primerica.tests.appointment.preparation.OpenCreateAppointmentForm;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CreateNewContactRecord.class,
	OpenCreateAppointmentForm.class,
	CheckInvalidDateEntries.class,
	CheckValidDateEntries.class
})
public class AppointmentSuiteTests {}
