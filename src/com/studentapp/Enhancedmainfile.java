package com.studentapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainConsoleInput {

    private static List<Student> studentList;
    private static Scanner sc;

    public static void main(String[] args) {

        System.out.println("====================== STUDENT MANAGEMENT SYSTEM ======================");

        studentList = new ArrayList<>();
        sc = new Scanner(System.in);

        while (true) {
            printMainMenu();
            int options = getValidIntegerInput();

            switch (options) {
                case 1:
                    enrollStudent();
                    break;
                case 2:
                    findStudentByIdInteractive();
                    break;
                case 3:
                    printAllStudentData();
                    break;
                case 4:
                    sortByNameAndPrint();
                    break;
                case 5:
                    exit();
                    break;
                default:
                    System.out.println("Invalid option selected!...Enter a number between 1 to 5");
                    break;
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("====================== WELCOME ======================");
        System.out.println("Select an option");
        System.out.println("1. Register a Student");
        System.out.println("2. Find Student with StudentId");
        System.out.println("3. List all Student information");
        System.out.println("4. List Student information in sorted order");
        System.out.println("5. Exit");
    }

    private static int getValidIntegerInput() {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine(); // Clear buffer
            }
        }
    }

    private static void exit() {
        System.out.println("Exiting... Thank you for using the Student Management System!");
        sc.close(); // Properly close the Scanner resource
        System.exit(0);
    }

    private static void printAllStudentData() {
        if (studentList.isEmpty()) {
            System.out.println("No students enrolled yet!");
        } else {
            for (Student student : studentList) {
                student.printStudentInfo();
            }
        }
        System.out.println("====================== ****************** ======================");
    }

    private static void findStudentByIdInteractive() {
        System.out.println("Enter the studentId to search for...");
        String studentId = sc.next();

        Student student = findStudentByIdusingStream(studentId);
        if (student != null) {
            student.printStudentInfo();
        }
    }

    private static void enrollStudent() {
        System.out.println("Enter the student Name:");
        String studentName = sc.next();

        System.out.println("Enter the student Age:");
        int studentAge = getValidIntegerInput();

        System.out.println("Enter the student ID:");
        String studentId = sc.next();

        // Prevent duplicate student IDs
        for (Student student : studentList) {
            if (student.getStudentID().equalsIgnoreCase(studentId)) {
                System.out.println("Student ID already exists. Try again.");
                return;
            }
        }

        Student newStudent = new Student(studentName, studentAge, studentId);
        studentList.add(newStudent);

        while (true) {
            System.out.println("Enter the course to be enrolled (Type 'done' to finish):");
            String courseName = sc.next();
            if (courseName.equalsIgnoreCase("done")) {
                break;
            }
            newStudent.enrollCourse(courseName);
        }
    }

    private static void sortByNameAndPrint() {
        if (studentList.isEmpty()) {
            System.out.println("No students enrolled yet!");
            return;
        }

        studentList.sort(Comparator.comparing(Student::getName));
        printAllStudentData();
    }

    public static Student findStudentByIdusingStream(String studentId) {
        Student result = null;
        try {
            result = studentList
                    .stream()
                    .filter(x -> x.getStudentID().equalsIgnoreCase(studentId))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No data found"));
        } catch (RuntimeException e) {
            System.err.println("THE GIVEN ID: " + studentId + " WAS NOT FOUND. PLEASE ENTER A VALID ID...");
        }
        return result;
    }
}
