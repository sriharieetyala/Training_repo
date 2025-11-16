package com.book;



import java.util.*;

public class Bookdetails {

    public int book_id;
    public String name;
    public List<Author> authors;
    public String publisher;
    public String num_pages;
    public EDITION edition;
    public Date date_of_printing;
    public int price;

    // Simple constructor
    public Bookdetails(
            int book_id,
            String name,
            List<Author> authors,
            String publisher,
            String num_pages,
            int price,
            EDITION edition,
            Date date_of_printing
    ) {
        this.book_id = book_id;
        this.name = name;
        this.authors = authors;
        this.publisher = publisher;
        this.num_pages = num_pages;
        this.price = price;
        this.edition = edition;
        this.date_of_printing = date_of_printing;
    }

    // Getter + Setter (only one shown for example)
    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    // VERY simple method
    public void showDetails() {
        System.out.println("Book: " + name);
        System.out.println("Publisher: " + publisher);
        System.out.println("Price: " + price);
    }
}
