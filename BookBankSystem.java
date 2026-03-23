import java.util.*;

class User {
    String username, password;
    public User(String u, String p) {
        this.username = u;
        this.password = p;
    }
}

class Book {
    String title;
    boolean isIssued;

    public Book(String title) {
        this.title = title;
        this.isIssued = false;
    }

    public String toString() {
        return title + (isIssued ? " (Issued)" : " (Available)");
    }
}

public class BookBankSystem {
    static Scanner sc = new Scanner(System.in);
    static List<User> customers = new ArrayList<>();
    static List<User> librarians = new ArrayList<>();
    static List<Book> books = new ArrayList<>();

    static {
        customers.add(new User("mageshwaran", "1234"));
	customers.add(new User("parasuraman", "1234"));
	customers.add(new User("dhilipkumar", "1234"));
	customers.add(new User("manikandan", "1234"));
        librarians.add(new User("mageshwaran", "1234"));
	librarians.add(new User("parasuraman", "1234"));
	librarians.add(new User("dhilipkumar", "1234"));
	librarians.add(new User("manikandan", "1234"));
        books.add(new Book("Java Basics"));
        books.add(new Book("Object Oriented Design"));
        books.add(new Book("Data Structures"));
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- BOOK BANK SYSTEM ---");
            System.out.println("1. Login as Customer");
            System.out.println("2. Login as Librarian");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (choice) {
                case 1 -> customerLogin();
                case 2 -> librarianLogin();
                case 3 -> {
                    System.out.println("Exiting. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    static void customerLogin() {
        System.out.print("Username: ");
        String u = sc.nextLine();
        System.out.print("Password: ");
        String p = sc.nextLine();

        for (User user : customers) {
            if (user.username.equals(u) && user.password.equals(p)) {
                System.out.println("Login successful. Welcome, " + u + "!");
                customerMenu();
                return;
            }
        }
        System.out.println("Login failed.");
    }

    static void librarianLogin() {
        System.out.print("Username: ");
        String u = sc.nextLine();
        System.out.print("Password: ");
        String p = sc.nextLine();

        for (User user : librarians) {
            if (user.username.equals(u) && user.password.equals(p)) {
                System.out.println("Librarian login successful.");
                librarianMenu();
                return;
            }
        }
        System.out.println("Login failed.");
    }

    static void customerMenu() {
        while (true) {
            System.out.println("\n-- Customer Menu --");
            System.out.println("1. View Book Details");
            System.out.println("2. Request Book");
            System.out.println("3. Collect Book");
            System.out.println("4. Logout");
            System.out.print("Choose option: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> viewBooks();
                case 2 -> requestBook();
                case 3 -> collectBook();
                case 4 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    static void librarianMenu() {
        while (true) {
            System.out.println("\n-- Librarian Menu --");
            System.out.println("1. Order New Book");
            System.out.println("2. Issue Book");
            System.out.println("3. View All Books");
            System.out.println("4. Logout");
            System.out.print("Choose option: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> orderBook();
                case 2 -> issueBook();
                case 3 -> viewBooks();
                case 4 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    static void viewBooks() {
        System.out.println("\n-- Book List --");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
    }

    static void requestBook() {
        viewBooks();
        System.out.print("Enter book number to request: ");
        int idx = sc.nextInt() - 1;
        if (idx >= 0 && idx < books.size()) {
            System.out.println("Requested: " + books.get(idx).title + ". We’ll notify you when it’s available.");
        } else {
            System.out.println("Invalid selection.");
        }
    }

    static void collectBook() {
        System.out.print("Enter book title to collect: ");
        String title = sc.nextLine();
        for (Book b : books) {
            if (b.title.equalsIgnoreCase(title) && !b.isIssued) {
                b.isIssued = true;
                System.out.println("Book collected successfully: " + b.title);
                return;
            }
        }
        System.out.println("Book not available for collection.");
    }

    static void orderBook() {
        System.out.print("Enter new book title to order: ");
        String title = sc.nextLine();
        books.add(new Book(title));
        System.out.println("Ordered and added new book: " + title);
    }

    static void issueBook() {
        viewBooks();
        System.out.print("Enter book number to issue: ");
        int idx = sc.nextInt() - 1;
        if (idx >= 0 && idx < books.size() && !books.get(idx).isIssued) {
            books.get(idx).isIssued = true;
            System.out.println("Book issued: " + books.get(idx).title);
        } else {
            System.out.println("Invalid selection or book already issued.");
        }
    }
}
