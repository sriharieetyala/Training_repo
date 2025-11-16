package com.book;

<<<<<<< HEAD
import java.util.List;

public class Bookdetails {

    private int id;
    private String publisher;
    private List<Author> authors;
    private String name;
    private int pages;
    private float price;
    private EDITION edition;   // keep EDITION if your enum name is EDITION

    public Bookdetails(int id, String publisher, List<Author> authors,
                       String name, int pages, float price, EDITION edition) {

        this.id = id;
        this.publisher = publisher;
        this.authors = authors;
        this.name = name;
        this.pages = pages;
        this.price = price;
        this.edition = edition;
    }

    public String getName() {
        return name;
=======


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
>>>>>>> b5ef42db800994b7f267c6ac4dfecd69d3a2700c
    }
}
