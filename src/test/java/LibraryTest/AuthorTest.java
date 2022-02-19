package LibraryTest;

import Library.Author;
import Library.Book;
import junit.framework.TestCase;
import org.junit.Before;

public class AuthorTest extends TestCase {
    private Author author;
    @Before
    public void setUp(){
        author=new Author("Raj Sharma",new Book("As a Man Thinketh","2018","4.75"));
    }

    public void testTestGetName() {
        assertEquals("Raj Sharma",author.getName());
    }

    public void testTestGetTitle() {
        assertEquals("As a Man Thinketh",author.getBooks().getTitle());
    }
    public void testGetYear() {
        assertEquals(2018,author.getBooks().getYear());
    }
    public void testGetRating() {
        assertEquals(4.75,author.getBooks().getRating());
    }

}