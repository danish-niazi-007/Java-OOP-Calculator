import java.io.*;
import java.util.*;

// Interface
interface Reportable {
    void generateReport();
}

// Abstract class
abstract class Person {
    protected String name;
    protected int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    abstract void displayDetails();
}

// Custom Exception
class InvalidMarksException extends Exception {
    InvalidMarksException(String msg) {
        super(msg);
    }
}

// Base Student Class
class Student extends Person implements Reportable {
    protected int marks;
    public static int studentCount = 0;

    Student(String name, int age, int marks) {
        super(name, age);
        this.marks = marks;
        studentCount++;
    }

    // Copy constructor
    Student(Student s) {
        super(s.name, s.age);
        this.marks = s.marks;
        studentCount++;
    }

    public void displayDetails() {
        System.out.println("Student: " + name + ", Age: " + age + ", Marks: " + marks);
    }

    public void generateReport() {
        System.out.println(name + "'s Report: Marks = " + marks);
    }
}

// Polymorphism - ScholarStudent
class ScholarStudent extends Student {
    private String scholarship;

    ScholarStudent(String name, int age, int marks, String scholarship) {
        super(name, age, marks);
        this.scholarship = scholarship;
    }

    // Method Overriding
    public void displayDetails() {
        System.out.println("Scholar Student: " + name + ", Age: " + age + ", Marks: " + marks + ", Scholarship: " + scholarship);
    }

    public void generateReport() {
        System.out.println(name + "'s Scholar Report: " + marks + " marks, Scholarship: " + scholarship);
    }
}

// File Handling Utility
class FileHandler {
    public static void writeToFile(String filename, String data) throws IOException {
        FileWriter fw = new FileWriter(filename, true);
        fw.write(data + "\n");
        fw.close();
    }

    public static void readFromFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        System.out.println("\n📄 File Contents:");
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }
}

// Main class
public class Poly_FileHandling {
    public static void main(String[] args) {
        try {
            // Regular student
            Student s1 = new Student("Ali", 20, 85);

            // Scholar student (polymorphism)
            Student s2 = new ScholarStudent("Sara", 22, 90, "Merit");

            // Copy constructor
            Student s3 = new Student(s1);

            // Polymorphism in action
            List<Student> studentList = new ArrayList<>();
            studentList.add(s1);
            studentList.add(s2);
            studentList.add(s3);

            // File Writing
            for (Student s : studentList) {
                s.displayDetails();  // dynamic method call
                s.generateReport();  // polymorphism
                FileHandler.writeToFile("student_data.txt", s.name + "," + s.age + "," + s.marks);
            }

            // Reading from file
            FileHandler.readFromFile("student_data.txt");

        } catch (IOException e) {
            System.out.println("File Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("\nTotal students created: " + Student.studentCount);
        }
    }
}
