// Interface
interface Borrowable {
    default void borrowBook(String bookName) {
        System.out.println("Borrowing book: " + bookName);
    }
}

// Abstract class
abstract class User {
    protected String name;
    protected int id;

    User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    abstract void displayDetails();
}

// Custom Exception
class BookNotFoundException extends Exception {
    BookNotFoundException(String message) {
        super(message);
    }
}

// Book class
class Book {
    private String title;
    private String author;
    public static int totalBooks = 0;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
        totalBooks++;
    }

    // Copy Constructor
    Book(Book b) {
        this.title = b.title;
        this.author = b.author;
        totalBooks++;
    }

    String getTitle() {
        return title;
    }

    void displayBook() {
        System.out.println("Title: " + title + ", Author: " + author);
    }
}

// Librarian extends User
class Librarian extends User {
    Librarian(String name, int id) {
        super(name, id);
    }

    void displayDetails() {
        System.out.println("Librarian Name: " + name + ", ID: " + id);
    }
}

// Admin extends Librarian (multilevel)
class Admin extends Librarian implements Borrowable {
    Admin(String name, int id) {
        super(name, id);
    }

    void issueBook(String bookName) throws BookNotFoundException {
        if (!bookName.equalsIgnoreCase("Java Fundamentals")) {
            throw new BookNotFoundException("Book not found: " + bookName);
        } else {
            borrowBook(bookName); // from interface
        }
    }
}

// Main Class
public class Libaray_OOPs {
    public static void main(String[] args) {
        try {
            Book b1 = new Book("Java Fundamentals", "James Gosling");
            Book b2 = new Book(b1); // Copy constructor

            Admin admin = new Admin("Danish", 101);
            admin.displayDetails();

            System.out.println("\nBook List:");
            b1.displayBook();
            b2.displayBook();

            admin.issueBook("Java Fundamentals");  // Valid book
             admin.issueBook("Python 101");     // Uncomment to test exception

        } catch (BookNotFoundException e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            System.out.println("Total books available: " + Book.totalBooks);
        }
    }
}
