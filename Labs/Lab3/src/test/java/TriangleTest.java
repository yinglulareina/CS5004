import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TriangleTest {
    private Triangle t1;
    private Triangle t2;
    private Triangle t3;

    @BeforeEach
    void setUp() {
        t1 = new Triangle(0, 0, 3, 0, 0, 4);
        t2 = new Triangle(0, 1, 0, 5, 5, 5);
        t3 = new Triangle(0, 0, 1, 1, 7, 7);


    }

    @Test
    void constructor_throwsWhenAnyPointIsNull() {
        Point2D p = new Point2D(0, 0);

        assertThrows(IllegalArgumentException.class,
                () -> new Triangle(null, p, p));

        assertThrows(IllegalArgumentException.class,
                () -> new Triangle(p, null, p));

        assertThrows(IllegalArgumentException.class,
                () -> new Triangle(p, p, null));
    }

    @Test
    void constructor_throwsWhenAnyTwoPointsIdentical() {
        //p1 == p2
        assertThrows(IllegalArgumentException.class,
                () -> new Triangle(0, 0, 0, 0, 7, 0));
        //p2 == p3
        assertThrows(IllegalArgumentException.class,
                () -> new Triangle(7, 0, 0, 0, 0, 0));
        // p1 == p3
        assertThrows(IllegalArgumentException.class,
                () -> new Triangle(0, 0, 7, 0, 0, 0));
    }

    @Test
    void constructor_throwsWhenThreePointsIdentical() {
        //p1 == p2 == p3
        assertThrows(IllegalArgumentException.class,
                () -> new Triangle(7, 7, 7, 7, 7, 7));
    }

    @Test
    void constructor_doesNotThrowWhenAllPointsDistinct_evenIfCollinear() {
        //p1 p2 p3 collinear
        assertDoesNotThrow(() -> new Triangle(0, 0, 1, 1, 7, 7));

        //normal triangle
        assertDoesNotThrow(() -> new Triangle(0, 0, 2, 3, 7, 9));
    }

    @Test
    void area() {
        assertEquals(6.0, t1.area(), 0.001);
        assertEquals(10, t2.area(), 0.001);
        assertEquals(0, t3.area(), 0.001);
    }

    @Test
    void perimeter() {
        assertEquals(12, t1.perimeter(), 0.001);
        assertEquals(4 + 5 + Math.sqrt(41), t2.perimeter(), 0.001);
    }

    @Test
    void resize() {
        double factor = 4.0;
        Triangle resizedT = (Triangle) t1.resize(factor);
        Triangle resizedT2 = (Triangle) t2.resize(factor);

        assertEquals(24.0, resizedT.area(), 0.001);
        assertEquals(40, resizedT2.area(), 0.001);
    }

    @Test
    void testToString() {
        String expected = "Triangle: p1(0.000,0.000) p2(3.000,0.000) p3(0.000,4.000)";
        String expected2 = "Triangle: p1(0.000,1.000) p2(0.000,5.000) p3(5.000,5.000)";
        assertEquals(expected, t1.toString());
        assertEquals(expected2, t2.toString());
    }
}