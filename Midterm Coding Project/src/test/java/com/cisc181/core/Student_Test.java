package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;

import java.util.GregorianCalendar;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eMajor;

public class Student_Test {

	private static ArrayList<Course> courses;
	private static ArrayList<Semester> semesters;
	private static ArrayList<Section> sections;
	private static ArrayList<Student> students;

	@BeforeClass
	public static void setup() throws PersonException {

		courses = new ArrayList<Course>();

		Course course1 = new Course("CISC181", 3, eMajor.COMPSI);
		Course course2 = new Course("CHEM103", 4, eMajor.CHEM);
		Course course3 = new Course("PHYS201", 4, eMajor.PHYSICS);

		courses.add(course1);
		courses.add(course2);
		courses.add(course3);

		semesters = new ArrayList<Semester>();

		Semester fall = new Semester((new GregorianCalendar(2017, 8, 28)).getTime(),
				(new GregorianCalendar(2017, 12, 16)).getTime());
		Semester spring = new Semester((new GregorianCalendar(2018, 2, 6)).getTime(),
				(new GregorianCalendar(2018, 5, 23)).getTime());

		semesters.add(fall);
		semesters.add(spring);

		sections = new ArrayList<Section>();

		Section fallCourse1 = new Section(courses.get(0).getCourseID(), fall.getSemesterID(), 201);
		Section fallCourse2 = new Section(courses.get(1).getCourseID(), fall.getSemesterID(), 202);
		Section fallCourse3 = new Section(courses.get(2).getCourseID(), fall.getSemesterID(), 203);
		Section springCourse1 = new Section(courses.get(0).getCourseID(), spring.getSemesterID(), 204);
		Section springCourse2 = new Section(courses.get(1).getCourseID(), spring.getSemesterID(), 205);
		Section springCourse3 = new Section(courses.get(2).getCourseID(), spring.getSemesterID(), 206);

		sections.add(fallCourse1);
		sections.add(fallCourse2);
		sections.add(fallCourse3);
		sections.add(springCourse1);
		sections.add(springCourse2);
		sections.add(springCourse3);

		students = new ArrayList<Student>();

		students.add(new Student("William", "W", "Barbes", (new GregorianCalendar(1997, 3, 21)).getTime(),
				eMajor.COMPSI, "1 Main St.", "302-111-1111", "wbarbes@udel.edu"));
		students.add(new Student("Morgan", "J", "Becker", (new GregorianCalendar(1997, 4, 21)).getTime(), eMajor.CHEM,
				"2 Main St.", "302-111-1112", "mbecker@udel.edu"));
		students.add(new Student("Jade", "C", "Carson", (new GregorianCalendar(1997, 5, 21)).getTime(), eMajor.BUSINESS,
				"3 Main St.", "302-111-1113", "jcarson@udel.edu"));
		students.add(new Student("Olivia", "O", "Castillo", (new GregorianCalendar(1997, 6, 21)).getTime(),
				eMajor.PHYSICS, "4 Main St.", "302-111-1114", "ocastillo@udel.edu"));
		students.add(new Student("William", "W", "Barbes", (new GregorianCalendar(1997, 7, 21)).getTime(),
				eMajor.COMPSI, "5 Main St.", "302-111-1115", "wbarbes@udel.edu"));
		students.add(new Student("Ryan", "B", "Davis", (new GregorianCalendar(1997, 8, 21)).getTime(), eMajor.NURSING,
				"6 Main St.", "302-111-1116", "rdavis@udel.edu"));
		students.add(new Student("James", "W", "Demers", (new GregorianCalendar(1996, 9, 21)).getTime(), eMajor.COMPSI,
				"7 Main St.", "302-111-1117", "jdemers@udel.edu"));
		students.add(new Student("Reed", "A", "Forman", (new GregorianCalendar(1996, 10, 21)).getTime(), eMajor.CHEM,
				"8 Main St.", "302-111-1118", "rforman@udel.edu"));
		students.add(new Student("Lee", "W", "Ennis", (new GregorianCalendar(1996, 11, 21)).getTime(), eMajor.PHYSICS,
				"9 Main St.", "302-111-1119", "lennis@udel.edu"));
		students.add(new Student("Jeesica", "E", "Ganim", (new GregorianCalendar(1996, 12, 21)).getTime(),
				eMajor.COMPSI, "10 Main St.", "302-111-1121", "jganim@udel.edu"));
	}

	@Test
	public void testEnrollment() {
		ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
		double grade = 100;
		double[] studentGPA = new double[10];

		for (Section section : sections) {
			for (Student student : students) {
				Enrollment enrollment = new Enrollment(student.getStudentID(), section.getSectionID());
				enrollments.add(enrollment);
				enrollment.setGrade(grade);
				grade--;
			}
		}

		int studentIndex = 0;
		for (Student student : students) {
			double poitsEarned = 0;
			double totalPoints = 0;

			for (Enrollment enrollment : enrollments) {
				if (enrollment.getStudentID() == student.getStudentID()) {
					UUID sectionID = enrollment.getSectionID();
					for (Section section : sections) {
						if (sectionID == section.getSectionID()) {
							UUID courseID = section.getCourseID();
							for (Course course : courses) {
								if (courseID == course.getCourseID()) {
									int gradePoints = course.getGradePoints();
									poitsEarned = poitsEarned + enrollment.getGrade() * gradePoints;
									totalPoints = totalPoints + 100 * gradePoints;

								}
							}
						}
					}
				}
			}
			studentGPA[studentIndex] = 4 * poitsEarned / totalPoints;
			studentIndex++;
		}
		assertEquals(studentGPA[0], 2.96, 0.01);
		assertEquals(studentGPA[1], 2.92, 0.01);
		assertEquals(studentGPA[2], 2.88, 0.01);
		assertEquals(studentGPA[3], 2.84, 0.01);
		assertEquals(studentGPA[4], 2.80, 0.01);
		assertEquals(studentGPA[5], 2.76, 0.01);
		assertEquals(studentGPA[6], 2.72, 0.01);
		assertEquals(studentGPA[7], 2.68, 0.01);
		assertEquals(studentGPA[8], 2.64, 0.01);
		assertEquals(studentGPA[9], 2.60, 0.01);

		double[] courseAvg = new double[3];
		int courseIndex = 0;
		for (Course course:courses) {
			double gradeSum = 0;
			double numOfStudent = 0;
			for (Section section:sections) {
				if (course.getCourseID() == section.getCourseID()) {
					for (Enrollment enrollment:enrollments) {
						if (enrollment.getSectionID() == section.getSectionID()) {
							gradeSum = gradeSum + enrollment.getGrade();
							numOfStudent++;
						}
					}
				}
			}
			courseAvg[courseIndex] = gradeSum/numOfStudent;
			courseIndex++;
		}
		assertEquals(courseAvg[0],80.5,0.1);
		assertEquals(courseAvg[1],70.5,0.1);
		assertEquals(courseAvg[2],60.5,0.1);
	}
}