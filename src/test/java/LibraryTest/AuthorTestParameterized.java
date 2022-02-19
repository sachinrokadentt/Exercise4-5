package LibraryTest;

import Library.Author;
import Library.Book;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;


@RunWith(Parameterized.class)
public class AuthorTestParameterized extends TestCase {

    private String authorName;
    Book book;
    private String expectedAuthor;
    private String bookName;
    private int year;
    private double rating;

    public AuthorTestParameterized(String authorName, Book book, String expectedAuthor, String bookName, int year, double rating) {
        this.authorName = authorName;
        this.book = book;
        this.expectedAuthor = expectedAuthor;
        this.bookName = bookName;
        this.year = year;
        this.rating = rating;
    }

    private Author author;
    @Before
    public void setUp(){
        author=new Author("Raj Sharma",new Book("As a Man Thinketh","2018","4.75"));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> multipleTest()
    {
        return Arrays.asList(new Object[][]{
                {"Raj Sharma",new Book("As a Man Thinketh","2018","4.75"),"Raj Sharma","As a Man Thinketh",2018,4.75},
                {"Ram Kohli",new Book("Brave","2008","4.05"),"Ram Kohli","Brave",2008,4.05},
                {"Sam Dhoni",new Book("Poor Dad","2005.0","3.95"),"Sam Dhoni","Poor Dad",2005,3.95},
                {"Ajay Dixit",new Book("Rich Dad Poor Dad","2015.0","4.55"),"Ajay Dixit","Rich Dad Poor Dad",2015,4.55}


        });
    }

    @org.junit.Test
    public void testTestGetName() {
        assertEquals(expectedAuthor,authorName);
    }
    @org.junit.Test
    public void testTestGetTitle() {
        assertEquals(bookName,book.getTitle());
    }
    @org.junit.Test
    public void testGetYear() {
        assertEquals(year,book.getYear());
    }
    @org.junit.Test
    public void testGetRating() {
        assertEquals(rating,book.getRating());
    }


}