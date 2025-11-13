
import java.time.LocalDate;


public class Transaction {
    private String bookId;
    private String memberId;
    private LocalDate borrowDate;

    public Transaction(String bookId, String memberId, LocalDate borrowDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = borrowDate;
    }

    // --- Getters ---
    public String getBookId() {
        return bookId;
    }

    public String getMemberId() {
        return memberId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }
}
