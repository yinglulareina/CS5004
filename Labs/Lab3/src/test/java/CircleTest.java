import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CircleTest {
    private Circle c1;
    private Circle c2;

    @BeforeEach
    void setUp() {
        c1 = new Circle(0, 0, 2);
        c2 = new Circle(1, 2, 0);
    }

    @Test
    void area() {
        assertEquals(12.566, c1.area(), 0.001);
        assertEquals(0, c2.area(), 0.001);
    }

    @Test
    void perimeter() {
        assertEquals(12.566, c1.perimeter(), 0.001);
        assertEquals(0, c2.perimeter(), 0.001);
    }

    @Test
    void resize() {
        double factor = 4.0;
        Circle resizedC = (Circle) c1.resize(factor);
        Circle resizedC2 = (Circle) c2.resize(factor);

        assertEquals(50.265, resizedC.area(), 0.001);
        assertEquals(0, resizedC2.area(), 0.001 );

    }

    @Test
    void testToString() {
        String expected = "Circle: center (0.000,0.000) radius 2.000";
        String expected2 = "Circle: center (1.000,2.000) radius 0.000";

        assertEquals(expected, c1.toString());
        assertEquals(expected2, c2.toString());
    }
}