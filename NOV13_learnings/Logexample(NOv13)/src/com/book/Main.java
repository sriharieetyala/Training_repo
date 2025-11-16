package com.book;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Main {

    public static void main(String[] args) {

        final Logger logger = LogManager.getLogger(Main.class);

        // Create authors list
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("John"));   // change name if needed

        // Create book object
        Bookdetails book = new Bookdetails(
                1,
                "TMH",
                authors,
                "Learn Java",
                100,
                110.2F,
                EDITION.A
        );

        // Logging the book name
        logger.debug(book.getName());
        logger.info(book.getName());

        // Sample try-catch with logging
        try {
            int x = 10 / 0;
            logger.info("Result: " + x);
        } catch (Exception e) {
            logger.error("Error occurred: ", e);
        }
    }
}
