package LibraryTest;

import Library.Book;
import junit.framework.TestCase;
import org.junit.Before;

public class BookTest extends TestCase {

    private Book book;

    @Before
    public void setUp(){
        book=new Book("Rich Dad Poor Dad","2015.0","4.55");

    }
    public void testTestGetTitle() {
        assertEquals("Rich Dad Poor Dad",book.getTitle());
    }
    public void testGetYear() {
        assertEquals(2015,book.getYear());
    }
    public void testGetRating() {
        assertEquals(4.55,book.getRating());
    }
}