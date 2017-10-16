package com.cisc181.core;

import java.util.UUID;

public class Enrollment {
	
	private UUID SectionID;
	private UUID StudentID;
	private UUID EnrollmentID;
	private double Grade;
	
	private Enrollment() {
		
	}
	
	public Enrollment(UUID studentID, UUID sectionID) {
		StudentID = studentID;
		SectionID = sectionID;
		EnrollmentID = UUID.randomUUID();
	}

	public UUID getSectionID() {
		return SectionID;
	}

	public UUID getStudentID() {
		return StudentID;
	}

	public UUID getEnrollmentID() {
		return EnrollmentID;
	}

	public double getGrade() {
		return Grade;
	}

	public void setGrade(double grade) {
		Grade = grade;
	}
	

	
}

