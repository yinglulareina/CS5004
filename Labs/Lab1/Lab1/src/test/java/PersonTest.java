import static org.junit.jupiter.api.Assertions.*;


class PersonTest {
    private Person alex;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        alex = new Person(
                "Alex White",
                "alex.white@gmail.com",
                "San Jose, CA, USA"
        );
    }

    @org.junit.jupiter.api.Test
    void getName() {
        assertEquals("Alex White", alex.getName());
    }

    @org.junit.jupiter.api.Test
    void getEmail() {
        assertEquals("alex.white@gmail.com", alex.getEmail());
    }

    @org.junit.jupiter.api.Test
    void getAddress() {
        assertEquals("San Jose, CA, USA", alex.getAddress());
    }
}