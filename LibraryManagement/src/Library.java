
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;


public class Library {

    // Using Maps for easy lookup by ID
    private Map<String, Book> books = new HashMap<>();
    private Map<String, Member> members = new HashMap<>();
    // Tracks active borrowings. Key is bookId, value is the Transaction.
    private Map<String, Transaction> activeTransactions = new HashMap<>();

    public static final int ALLOWED_BORROW_DAYS = 7;
    public static final double FINE_PER_DAY = 10.0; // Rs. 10 per day

    public Library() {
        // Pre-populate with some data
        books.put("B1", new Book("B1", "Harry Potter", "JK Rowling"));
        books.put("B2", new Book("B2", "1984", "George Orwell"));
        books.put("B3", new Book("B3", "To Kill a Mockingbird", "Harper Lee"));

        members.put("M1", new Member("M1", "Srihari"));
        members.put("M2", new Member("M2", "Irahirs"));
    }

    /**
     * Borrows a book for a member on a specific date.
     * @throws LibraryException if any business rule is violated.
     */
    public void borrowBook(String memberId, String bookId, LocalDate borrowDate) throws LibraryException {
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);

        if (!book.isAvailable()) {
            throw new BookUnavailableException("Book '" + book.getTitle() + "' is already borrowed.");
        }

        // Process the borrowing
        book.setAvailable(false);
        Transaction transaction = new Transaction(bookId, memberId, borrowDate);
        activeTransactions.put(bookId, transaction);

        System.out.println("Success: " + member.getName() + " borrowed '" + book.getTitle() + "' on " + borrowDate);
    }

    /**
     * Returns a book from a member on a specific date and calculates fines.
     * @return The calculated fine (0.0 if no fine).
     * @throws LibraryException if any business rule is violated.
     */
    public double returnBook(String memberId, String bookId, LocalDate returnDate) throws LibraryException {
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);

        if (book.isAvailable()) {
            throw new BookNotBorrowedException("Book '" + book.getTitle() + "' is not currently borrowed.");
        }

        Transaction transaction = activeTransactions.get(bookId);
        if (transaction == null || !transaction.getMemberId().equals(memberId)) {
            throw new BookNotBorrowedException("Book '" + book.getTitle() + "' was not borrowed by " + member.getName());
        }

        // Process the return
        book.setAvailable(true);
        activeTransactions.remove(bookId);

        // Calculate fine
        double fine = calculateFine(transaction.getBorrowDate(), returnDate);
        
        System.out.println("Success: " + member.getName() + " returned '" + book.getTitle() + "' on " + returnDate);
        return fine;
    }

    /**
     * Calculates the fine based on borrow and return dates.
     */
    private double calculateFine(LocalDate borrowDate, LocalDate returnDate) {
        long daysBorrowed = ChronoUnit.DAYS.between(borrowDate, returnDate);

        if (daysBorrowed > ALLOWED_BORROW_DAYS) {
            long overdueDays = daysBorrowed - ALLOWED_BORROW_DAYS;
            return overdueDays * FINE_PER_DAY;
        }
        
        return 0.0; // No fine
    }

    // --- Helper methods to find entities ---

    public Book findBookById(String bookId) throws BookNotFoundException {
        Book book = books.get(bookId);
        if (book == null) {
            throw new BookNotFoundException("No book found with ID: " + bookId);
        }
        return book;
    }

    public Member findMemberById(String memberId) throws MemberNotFoundException {
        Member member = members.get(memberId);
        if (member == null) {
            throw new MemberNotFoundException("No member found with ID: " + memberId);
        }
        return member;
    }

    // --- Methods to show available data ---
    public void listAvailableBooks() {
        System.out.println("\n--- Available Books ---");
        books.values().stream()
             .filter(Book::isAvailable)
             .forEach(System.out::println);
        System.out.println("-------------------------");
    }

    public void listMembers() {
        System.out.println("\n--- Library Members ---");
        members.values().forEach(System.out::println);
        System.out.println("-----------------------");
    }
}
