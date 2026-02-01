import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShapeTest {

    @Test
    void distanceFromOrigin_usesReferencePoint() {
        Shape c = new Circle(3, 4, 5);
        Shape r = new Rectangle(6, 8, 2, 3);

        assertEquals(5.0, c.distanceFromOrigin(), 0.001);
        assertEquals(10.0, r.distanceFromOrigin(), 0.001);
    }

    @Test
    void compareTo_hasThreeOutcomes() {
        Shape small = new Rectangle(0, 0, 1, 1);
        Shape big = new Circle(0, 0, 2);
        Shape sameAsSmall = new Rectangle(0, 0, 0.5, 2);

        assertTrue(small.compareTo(big) < 0);
        assertTrue(big.compareTo(small) > 0);
        assertEquals(0, sameAsSmall.compareTo(small));

    }
}
