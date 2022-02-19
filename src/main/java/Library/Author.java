package Library;

import java.util.ArrayList;

public class Author {
    private String  name;
    Book books;

    public Author(String name, Book books) {
        this.name = name;
        this.books = books;
    }


    public String getName() {
        return name;
    }

    public Book getBooks() {
        return books;
    }

}
