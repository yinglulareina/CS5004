import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookTest {

    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    void setUp() {
        book1 = new Book("Clean Code", "Robert C. Martin", 464);
        book2 = new Book("The Pragmatic Programmer", "Andrew Hunt", 352);
        book3 = new Book("Effective Java", "Joshua Bloch", 416);
    }

    @Test
    void getTitle() {
        assertEquals("Clean Code", book1.getTitle());
        assertEquals("The Pragmatic Programmer", book2.getTitle());
        assertEquals("Effective Java", book3.getTitle());
    }

    @Test
    void getAuthor() {
        assertEquals("Robert C. Martin", book1.getAuthor());
        assertEquals("Andrew Hunt", book2.getAuthor());
        assertEquals("Joshua Bloch", book3.getAuthor());
    }

    @Test
    void getPages() {
        assertEquals(464, book1.getPages());
        assertEquals(352, book2.getPages());
        assertEquals(416, book3.getPages());
    }
}
