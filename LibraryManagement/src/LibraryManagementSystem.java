import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Main class with the user interface and program loop.
 */
public class LibraryManagementSystem {

    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);
    // Defines the date format we expect from the user
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        String userChoice;
        do {
            showMenu();
            userChoice = scanner.nextLine();
            boolean continueTransactions;

            switch (userChoice) {
                case "1":
                    continueTransactions = handleBorrowBook();
                    break;
                case "2":
                    continueTransactions = handleReturnBook();
                    break;
                case "3":
                    System.out.println("Exiting system. Goodbye!");
                    continueTransactions = false; // Force exit
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                    continueTransactions = true; // Loop again
            }

            if (!continueTransactions) {
                break; // Exit the main do-while loop
            }

            // This part handles the "do you want any other book" logic
            if (!userChoice.equals("3")) { // Don't ask if they just chose "Exit"
                 if (!askToContinue()) {
                     break; // Exit if they say no
                 }
            }

        } while (true); // The loop is controlled by 'break' statements

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n===== Library Management System =====");
        System.out.println("1. Borrow a Book");
        System.out.println("2. Return a Book");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }
    
    /**
     * Asks the user if they want to perform another transaction.
     * @return true if yes, false if no.
     */
    private static boolean askToContinue() {
        System.out.print("\nDo you want to perform another transaction? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes");
    }

    /**
     * Handles all logic for borrowing a book.
     * @return true if the transaction was successful, false otherwise.
     */
    private static boolean handleBorrowBook() {
        try {
            library.listMembers();
            System.out.print("Enter your Member ID: ");
            String memberId = scanner.nextLine();

            library.listAvailableBooks();
            System.out.print("Enter the Book ID you want to borrow: ");
            String bookId = scanner.nextLine();

            // Ask for the current date as requested
            LocalDate borrowDate = readDate("Enter the CURRENT date (yyyy-MM-dd): ");

            library.borrowBook(memberId, bookId, borrowDate);
            return true; // Transaction successful

        } catch (LibraryException e) {
            // Catch any of our custom exceptions
            System.err.println("Error: " + e.getMessage());
            return false; // Transaction failed
        } catch (Exception e) {
            // Catch other potential errors (like bad date format)
            System.err.println("An unexpected error occurred: " + e.getMessage());
            return false;
        }
    }

    /**
     * Handles all logic for returning a book.
     * @return true if the transaction was successful, false otherwise.
     */
    private static boolean handleReturnBook() {
         try {
            System.out.print("Enter your Member ID: ");
            String memberId = scanner.nextLine();

            System.out.print("Enter the Book ID you are returning: ");
            String bookId = scanner.nextLine();

            // Ask for the current date as requested
            LocalDate returnDate = readDate("Enter the CURRENT date (yyyy-MM-dd): ");

            double fine = library.returnBook(memberId, bookId, returnDate);

            if (fine > 0) {
                System.out.println("IMPORTANT: A fine of Rs." + fine + " has been applied for the late return.");
            } else {
                System.out.println("Book returned on time. No fine.");
            }
            return true; // Transaction successful

        } catch (LibraryException e) {
            System.err.println("Error: " + e.getMessage());
            return false; // Transaction failed
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            return false;
        }
    }

    /**
     * Helper method to repeatedly ask for a date until it's in the correct format.
     */
    private static LocalDate readDate(String prompt) {
        LocalDate date = null;
        while (date == null) {
            try {
                System.out.print(prompt);
                String dateInput = scanner.nextLine();
                date = LocalDate.parse(dateInput, dateFormatter);
            } catch (DateTimeParseException e) {
                System.err.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }
        return date;
    }
}
