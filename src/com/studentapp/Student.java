package com.studentapp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Student {

	private String name;
	private int age;
	private String studentID;
	private List<String> courses;

	public Student(String name, int age, String studentID) {
		super();
		if(validateAge(age) && validateName(name) && validateIdnumber(studentID)) {
			this.name = name;
			this.age = age;
			this.studentID = studentID;
			courses = new ArrayList<String>();	
		}
		
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getStudentID() {
		return studentID;
	}



	public void enrollCourse(String coureseName) {
		if(validateEnrollCourse(coureseName)) {
			if (!courses.contains(coureseName)) {
				courses.add(coureseName);
				System.out.println("STUDENT IS ENROLLED TO THE COURSE : "+coureseName +" SUCCESSFULLY...");
			}
			else {
				System.err.println("STUDENT IS ALREADY ENROLLED TO THE COURSE "+coureseName);
			}
		}
		
	}

	public void printStudentInfo() {

		System.out.println("====================== STUDENT INFORMATON ======================");

		System.out.println("STUDENT NAME            : " + name);
		System.out.println("STUDENT AGE             : " + age);
		System.out.println("STUDENT STUDENT ID      : " + studentID);
		System.out.println("STUDENT ENROLLED COURSE : " + courses);
		
	}
	
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", studentID=" + studentID + ", courses=" + courses + "]";
	}
	
	
	
	
	
	//---------------------------------------
	//VALIDATION FOR CONSTRUCTOR
	//----------------------------------------
	
	public boolean validateAge(int age) {
		if (age > 19 && age < 35) {
			return true;
		} else {
			System.err.println("AGE==> PLEASE ENTER THE AGE BETWEEN 19 TO 35");
			return false;
		}
	}

	
	public boolean validateName(String name) {
		String regex = "^[A-Za-z][A-Za-z\\s]*[A-Za-z]$";
		boolean matches = Pattern.matches(regex, name);

		if (matches) {
			return true;
		} else {
			System.err.println("NAME==> PLEASE STARTS WITH ALPHABET , ENDS WITH ALPHABET AND IN BETWEEN ALLOWED TO USE SPACE ");
			return false;
		}

	}
	
	
	public boolean validateIdnumber(String studentId) {
		
		String regex = "^[sS]-\\d+$";
		boolean matches = Pattern.matches(regex, studentId);

		if (matches) {
			return true;
		} else {
			System.err.println("STUDENTID==> PLEASE STARTS WITH \"S-\"" + " AND IT SHOULD ENDS WITH DIGIT...");
			return false;
		}
		
	}
	
	
		//---------------------------------------
		//VALIDATION FOR METHOD
		//----------------------------------------
	
	public boolean validateEnrollCourse(String coureName) {

		if (coureName.equalsIgnoreCase("JAVA") || coureName.equalsIgnoreCase("DSA")
				|| coureName.equalsIgnoreCase("DEVOPS") || coureName.equalsIgnoreCase("SELENIUM")) {
			return true;
		} else {
			System.err.println("CHOSEN COURSE : " + coureName
					+ " WERE NOT AVAILALBLE , PLEASE CHOOSE FROM THE AVAILABLE COURSES==> [JAVA , DSA , DEVOPS , SELENIUM]");
			return false;
		}

	}



}
