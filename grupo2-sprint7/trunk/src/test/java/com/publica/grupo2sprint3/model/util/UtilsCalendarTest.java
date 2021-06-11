package com.publica.grupo2sprint3.model.util;

import static org.testng.Assert.assertEquals;

import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Test;


public class UtilsCalendarTest {
	Calendar calendar = UtilsCalendar.toCalendar("25/06/2004");
	
	@Test
	public void toCalendarTest() {
		assertEquals(calendar.get(Calendar.DAY_OF_MONTH), 25);
		assertEquals(calendar.get(Calendar.MONTH), 06);
		assertEquals(calendar.get(Calendar.YEAR), 2004);
	}

	@Test
	public void toMinutesTest() {
		Assert.assertEquals(UtilsCalendar.toMinutes("01:00"), 60);
		Assert.assertEquals(UtilsCalendar.toMinutes("02:00"), 120);
		Assert.assertEquals(UtilsCalendar.toMinutes("01:59"), 119);
		Assert.assertEquals(UtilsCalendar.toMinutes("00:00"), 00);
	}

	@Test
	public void toStringTestCalendarboolean() {
		Assert.assertEquals(UtilsCalendar.toString(calendar, false), "25/06");
		Assert.assertEquals(UtilsCalendar.toString(calendar, true), "25/06/2004");
	}

	@Test
	public void toStringTestint() {
		Assert.assertEquals(UtilsCalendar.toString(60), "01:00");
		Assert.assertEquals(UtilsCalendar.toString(120), "02:00");
		Assert.assertEquals(UtilsCalendar.toString(119), "01:59");
		Assert.assertEquals(UtilsCalendar.toString(0), "00:00");

	}
}
