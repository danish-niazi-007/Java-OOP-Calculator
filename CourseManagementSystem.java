// ✅ Project: Course Management System (Java OOP + File Handling + ArrayList + Encapsulation)
// Concepts Covered:
// - ArrayList
// - Abstraction, Interface, Inheritance, Polymorphism
// - Constructors (Default, Parameterized, Copy)
// - Encapsulation (Private data, Getter/Setter)
// - File Handling (Write/Read)
// - Static, this, super keywords

import java.io.*;
import java.util.*;

// Interface for Reporting
interface Reportable {
    void generateReport();
}

// Abstract Person class
abstract class Person {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    abstract void displayDetails();
}

// Course class (for Enrollment)
class Course {
    private String courseName;
    private int courseCode;

    public Course(String courseName, int courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public String toString() {
        return courseName + " (" + courseCode + ")";
    }
}

// Student class
class Student extends Person implements Reportable {
    private int id;
    private List<Course> courses = new ArrayList<>();
    private static int count = 0;

    // Default Constructor
    public Student() {
        super("Unknown", 0);
        this.id = ++count;
    }

    // Parameterized Constructor
    public Student(String name, int age) {
        super(name, age);
        this.id = ++count;
    }

    // Copy Constructor
    public Student(Student s) {
        super(s.name, s.age);
        this.id = ++count;
        this.courses = new ArrayList<>(s.courses);
    }

    // Getter
    public int getId() {
        return id;
    }

    // Add Course
    public void enrollCourse(Course c) {
        courses.add(c);
        System.out.println(this.name + " enrolled in: " + c.getCourseName());
    }

    public void displayDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Enrolled Courses:");
        for (Course c : courses) {
            System.out.println(" - " + c);
        }
    }

    public void generateReport() {
        System.out.println("Generating report for Student ID: " + id + ", Name: " + name);
    }

    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append(", Name: ").append(name)
                .append(", Age: ").append(age).append(", Courses: ");
        for (Course c : courses) {
            sb.append(c.getCourseName()).append("|");
        }
        return sb.toString();
    }
}

// File Handler Class
class FileHandler {
    public static void saveToFile(List<Student> students, String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        for (Student s : students) {
            fw.write(s.toFileString() + "\n");
        }
        fw.close();
    }

    public static void readFromFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }
}

// Main Class
public class CourseManagementSystem {
    public static void main(String[] args) {
        try {
            // Create some courses
            Course java = new Course("Java", 101);
            Course python = new Course("Python", 102);
            Course dsa = new Course("DSA", 103);

            // Create students
            Student s1 = new Student("Danish", 20);
            Student s2 = new Student("Ali", 21);

            // Enroll in courses
            s1.enrollCourse(java);
            s1.enrollCourse(dsa);

            s2.enrollCourse(python);

            // Use copy constructor
            Student s3 = new Student(s1);
            s3.enrollCourse(python);

            // Store in list
            List<Student> studentList = new ArrayList<>();
            studentList.add(s1);
            studentList.add(s2);
            studentList.add(s3);

            // Display & generate reports
            for (Student s : studentList) {
                s.displayDetails();
                s.generateReport();
                System.out.println();
            }

            // File handling
            String fileName = "students.txt";
            FileHandler.saveToFile(studentList, fileName);

            System.out.println("\n--- Reading from File ---");
            FileHandler.readFromFile(fileName);

        } catch (IOException e) {
            System.out.println("File Error: " + e.getMessage());
        }
    }
}
