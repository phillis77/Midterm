package com.cisc181.core;

import java.util.Date;

public class PersonException extends Exception {
	private Date DOB;
	private String Phone_number;
	
	public PersonException(Date newDOB) throws PersonException {
		this.DOB = newDOB;
	}

	public PersonException(String newPhone_number) throws PersonException {
		this.Phone_number = newPhone_number;
	}

	public Date getDOB() {
		return DOB;
	}


	public String getPhone_number() {
		return Phone_number;
	}

	

}
