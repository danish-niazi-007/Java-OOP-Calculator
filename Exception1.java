// ✅ Project: Student Result Management System
// Concepts Covered:
// - Classes and Objects
// - Access Modifiers
// - Static Keyword
// - Constructors (Default, Parameterized, Copy)
// - Abstraction (Abstract Class vs Interface)
// - Exception Handling (try-catch-finally, throws, custom exception)

// STEP 1: Interface (for reportable behavior)
interface Reportable {
    void generateReport();
}

// STEP 2: Abstract Class
abstract class Person {
    protected String name;
    protected int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    abstract void displayDetails();
}

// STEP 3: Custom Exception
class FailException extends Exception {
    FailException(String message) {
        super(message);
    }
}

// STEP 4: Main Student Class (extends Abstract and implements Interface)
class Student extends Person implements Reportable {
    private int marks;
    public static int studentCount = 0;

    // Parameterized Constructor
    public Student(String name, int age, int marks) {
        super(name, age);
        this.marks = marks;
        studentCount++;
    }

    // Copy Constructor
    public Student(Student s) {
        super(s.name, s.age);
        this.marks = s.marks;
        studentCount++;
    }

    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Marks: " + marks);
    }

    public void checkResult() throws FailException {
        if (marks < 40) {
            throw new FailException("Student has failed the exam.");
        } else {
            System.out.println("Status: Passed");
        }
    }

    public void generateReport() {
        System.out.println("Generating academic report for " + name);
    }
}

// STEP 5: Main Class
public class Exception1 {
    public static void main(String[] args) {
        try {
            Student s1 = new Student("Danish", 20, 50);  // Pass case
            Student s2 = new Student(s1);  // Copy Constructor

            s1.displayDetails();
            s1.generateReport();
            s1.checkResult();

        } catch (FailException e) {
            System.out.println("Custom Exception Caught: " + e.getMessage());
        } finally {
            System.out.println("Total Students Created: " + Student.studentCount);
        }
    }
}
