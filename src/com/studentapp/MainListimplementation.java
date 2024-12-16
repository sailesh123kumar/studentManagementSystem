package com.studentapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainListimplementation {

	static List<Student> studentList;

	public static void main(String[] args) {

		System.out.println("====================== STUDENT MANAGEMENT SYSTEM ======================");
		System.out.println("====================== WELCOME ======================");

		studentList = new ArrayList<Student>();

		Student stu1 = new Student("sailesh", 30, "S-201");
		Student stu2 = new Student("Asha", 28, "S-202");
		Student stu3 = new Student("Prathiksha", 20, "S-203");

		stu1.enrollCourse("JAVA");
		stu1.enrollCourse("DSA");
		stu1.enrollCourse("DEVOPS");
		stu2.enrollCourse("DSA");
		stu2.enrollCourse("SELENIUM");
		stu3.enrollCourse("DEVOPS");

		studentList.add(stu1);
		studentList.add(stu2);
		studentList.add(stu3);

		System.out.println(studentList);
		findStudentById("S-202");
		
		Student studentByIdusingStream = findStudentByIdusingStream("S-20");
		//studentByIdusingStream.printStudentInfo();
		
		System.out.println(studentByIdusingStream);
		
		System.out.println(studentList);
		//sortByName();
		sortByNameUsingLamda();

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
