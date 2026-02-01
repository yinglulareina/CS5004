import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RectangleTest {
    private Rectangle r1;
    private Rectangle r2;

    @BeforeEach
    void setUp() {
        r1 = new Rectangle(0, 0, 3, 2);
        r2 = new Rectangle(1, 2, 0, 0);
    }

    @Test
    void area() {
        assertEquals(6, r1.area(), 0.001);
        assertEquals(0, r2.area(), 0.001);
    }

    @Test
    void perimeter() {
        assertEquals(10, r1.perimeter(), 0.001);
        assertEquals(0, r2.perimeter(), 0.001);
    }

    @Test
    void resize() {
        double factor = 4;
        Rectangle resizedR = (Rectangle) r1.resize(factor);
        Rectangle resizedR2 = (Rectangle) r2.resize(factor);

        assertEquals(24, resizedR.area(), 0.001 );
        assertEquals(0, resizedR2.area(), 0.001);
    }

    @Test
    void testToString() {
        String expected = "Rectangle: LL corner (0.000,0.000) width 3.000 height 2.000";
        String expected2 = "Rectangle: LL corner (1.000,2.000) width 0.000 height 0.000";
        assertEquals(expected, r1.toString());
        assertEquals(expected2, r2.toString());
    }
}