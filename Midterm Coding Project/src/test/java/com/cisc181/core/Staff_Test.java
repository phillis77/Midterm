package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.GregorianCalendar;
import com.cisc181.eNums.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class Staff_Test {

	@BeforeClass
	public static void setup() {
	}

	@Test
	public void testAvgSalary() throws PersonException {
		ArrayList<Staff> staffs = new ArrayList<Staff>();
		staffs.add(new Staff("Terry", "J", "Eddington", (new GregorianCalendar(1989, 3, 21)).getTime(),
				"1459 Locust St.", "313-555-1111", "teddington@123.com", "Mon 1:00 - 2:00pm", 4, 5200.2,
				(new GregorianCalendar(2011, 9, 1)).getTime(), eTitle.MRS));
		staffs.add(new Staff("Sarah", "W", "Harmon", (new GregorianCalendar(1988, 5, 11)).getTime(), "4188 Jackson Ct.",
				"313-555-2222", "sharmon@123.com", "Tue 1:00 - 2:00pm", 3, 6200.2,
				(new GregorianCalendar(2011, 9, 1)).getTime(), eTitle.MS));
		staffs.add(new Staff("Dennis", "B", "Cooper", (new GregorianCalendar(1985, 8, 23)).getTime(), "6900 Martin Dr.",
				"313-555-3333", "dcooper@123.com", "Wed 1:00 - 2:00pm", 1, 7200.2,
				(new GregorianCalendar(2011, 9, 1)).getTime(), eTitle.MR));
		staffs.add(new Staff("Chester", "B", "Fink", (new GregorianCalendar(1981, 9, 22)).getTime(),
				"2451 Woodbury Dr.", "313-555-4444", "cfink@123.com", "Thur 1:00 - 2:00pm", 5, 4200.2,
				(new GregorianCalendar(2011, 9, 1)).getTime(), eTitle.MR));
		staffs.add(new Staff("Jonathon", "T", "Beards", (new GregorianCalendar(1979, 3, 1)).getTime(),
				"3926 Market St.", "313-555-5555", "jbeards@123.com", "Fri 1:00 - 2:00pm", 2, 7000.2,
				(new GregorianCalendar(2011, 9, 1)).getTime(), eTitle.MS));
		double salarySum = 0;
		for (Staff staff : staffs) {
			salarySum = salarySum + staff.getSalary();
		}
		double salaryAvg = salarySum / staffs.size();
		assertEquals(salaryAvg, 5960.2, .001);
	}

	@Test(expected = PersonException.class)
	public void testInvalidDOB() throws PersonException{
		
		new Staff("Terry", "J", "Eddington", (new GregorianCalendar(1916, 3, 21)).getTime(),
				"1459 Locust St.", "313-555-1111", "teddington@123.com", "Mon 1:00 - 2:00pm", 4, 5200.2,
				(new GregorianCalendar(2011, 9, 1)).getTime(), eTitle.MRS);
		new Staff("Terry", "J", "Eddington", (new GregorianCalendar(1917, 3, 21)).getTime(),
				"1459 Locust St.", "313-555-1111", "teddington@123.com", "Mon 1:00 - 2:00pm", 4, 5200.2,
				(new GregorianCalendar(2011, 9, 1)).getTime(), eTitle.MRS);
		new Staff("Terry", "J", "Eddington", (new GregorianCalendar(1917, 10,1)).getTime(),
				"1459 Locust St.", "313-555-1111", "teddington@123.com", "Mon 1:00 - 2:00pm", 4, 5200.2,
				(new GregorianCalendar(2011, 9, 1)).getTime(), eTitle.MRS);
				
	}
	
	@Test(expected = PersonException.class)
	public void testInvalidPhone() throws PersonException {
		new Staff("Terry", "J", "Eddington", (new GregorianCalendar(1989, 3, 21)).getTime(),
				"1459 Locust St.", "33-555-1111", "teddington@123.com", "Mon 1:00 - 2:00pm", 4, 5200.2,
				(new GregorianCalendar(2011, 9, 1)).getTime(), eTitle.MRS);
	}
}
