package Library;
import java.util.ArrayList;

public class Author {
    private String  name; //Author name
    Book books;    //Book class references

    //ParameterizeConstructor for initializing members
    public Author(String name, Book books) {
        this.name = name;
        this.books = books;
    }

    //getter methods for variable
    public String getName() {
        return name;
    }

    public Book getBooks() {
        return books;
    }

}
