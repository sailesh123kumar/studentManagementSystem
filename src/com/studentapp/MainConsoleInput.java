package com.studentapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MainConsoleInput {

	private static List<Student> studentList;
	private static Scanner sc;

	public static void main(String[] args) {

		System.out.println("====================== STUDENT MANAGEMENT SYSTEM ======================");
		

		studentList = new ArrayList<Student>();
		while(true) {
			System.out.println("====================== WELCOME ======================");
			System.out.println("Select an option");
			System.out.println("1. Register an Student");
			System.out.println("2. Find Student with StudentId");
			System.out.println("3. List all Student information");
			System.out.println("4. List Student information in sorted order");
			System.out.println("5. Exit");

			sc = new Scanner(System.in);
			int options = sc.nextInt();
			
			switch (options) {
			case 1:
				enrollStudent();
				break;
			case 2:
				findStudentById();
				break;
			case 3:
				printAllStudentData();
				break;
			case 4:
				sortByNameUsingLamda();
				break;
			case 5:
				exit();
				break;

			default:
				System.out.println("Invalid option selected!...Enter between 1 to 5");
				break;
			}
		}
	}

	

	private static void exit() {
		System.exit(0);		
	}



	private static void printAllStudentData() {
		for (Student student : studentList) {
			student.printStudentInfo();
			
		}
		System.out.println("====================== ****************** ======================");
	}

	private static void findStudentById() {
		System.out.println("Enter the studentId to search for...");
		String studentId = sc.next();
		
		for (Student student : studentList) {
			if(student.getStudentID().equalsIgnoreCase(studentId)) {
				student.printStudentInfo();
			}
		}
		
	}

	private static void enrollStudent() {
		
		System.out.println("Enter the student Name");
		String studentName = sc.next();
		
		System.out.println("Enter the student Age");
		int studentAge = sc.nextInt();
		
		System.out.println("Enter the student Id");
		String studentId = sc.next();
		
		Student stu1 = new Student(studentName, studentAge, studentId);
		
		studentList.add(stu1);
		while(true) {
			System.out.println("Enter the course to be enrolled!!...Type done to exit");
			String courseName = sc.next();
			if(courseName.equalsIgnoreCase("done")) {
				break;
			}
			stu1.enrollCourse(courseName);
		}
		
	}

	private static void sortByNameUsingLamda() {
		Comparator<Student> studentNameComparator = (s1,s2) -> s1.getName().compareTo(s2.getName());
		
		Collections.sort(studentList,studentNameComparator);
		System.out.println(studentList);
		
		
	}

	private static void sortByName() {

		Comparator<Student> studentNameComparator = new Comparator<Student>() {
			
			@Override
			public int compare(Student o1, Student o2) {
				return o1.getName().compareTo(o2.getName());
			}
		};
		Collections.sort(studentList,studentNameComparator);
		System.out.println(studentList);
	}

	public static void findStudentById(String studentId) {

		boolean flag = false;
		
		for (Student student : studentList) {
			if(student.getStudentID().equalsIgnoreCase(studentId)) {
				student.printStudentInfo();
				flag = true;
				break;
			}
		}
		if(flag==false) {
			System.err.println("THE GIVEN ID : " +studentId+ " WERE NOT FOUND, PLEASE PASS THE VALID ID...");
		}
	}
	
	public static Student findStudentByIdusingStream(String studentId) {
		Student result = null;
		try {
			result = studentList
					.stream()
						.filter(x -> x.getStudentID().equalsIgnoreCase(studentId))
							.findFirst()
								.orElseThrow(()->new RuntimeException("NO DATA FOUND"));
							
		}
		catch (RuntimeException e) {
			System.err.println("THE GIVEN ID : " +studentId+ " WERE NOT FOUND, PLEASE PASS THE VALID ID...");
		}
		return result;
	}
	
	

}
