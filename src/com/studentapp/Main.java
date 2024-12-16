package com.studentapp;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("====================== STUDENT MANAGEMENT SYSTEM ======================");
		System.out.println("====================== WELCOME ======================");
		
		Student stu1 =new Student("sailesh", 30, "S-201");
		Student stu2 =new Student("Asha", 28, "S-202");
		Student stu3 =new Student("Prathiksha", 20, "S-203");
		
		stu1.enrollCourse("JAVA");
		stu1.enrollCourse("DSA");
		stu1.enrollCourse("DEVOPS");
		stu2.enrollCourse("DSA");
		stu2.enrollCourse("SELENIUM");
		stu3.enrollCourse("DEVOPS");
		stu3.enrollCourse("AWS");
		
		System.out.println("TO STRING METHOD OVERRIDED===> "+stu1);
			
		stu1.printStudentInfo();
		stu2.printStudentInfo();
		stu3.printStudentInfo();
		
	}
	
	
}
