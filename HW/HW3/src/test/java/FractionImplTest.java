import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
/**
 * JUnit 5 tests for FractionImpl.
 * Covers all deliverables specified in the checklist.
 */
class FractionImplTest {
    private Fraction f12; //1/2
    private Fraction fNeg12; //-1/2

    @BeforeEach
    void setUp() {
        f12 = new FractionImpl(1, 2);
        fNeg12 = new FractionImpl(-1, 2);
    }

    @Test
    void testConstructorNormalization() {
        //denominator should always be positive
        Fraction f = new FractionImpl(-1, -2);
        assertEquals(1, f.getNumerator());
        assertEquals(2, f.getDenominator());

        Fraction f2 = new FractionImpl(1, -2);
        assertEquals(-1, f2.getNumerator());
        assertEquals(2, f2.getDenominator());

        //simplify
        Fraction f3 = new FractionImpl(2, 4);
        assertEquals(1, f3.getNumerator());
        assertEquals(2, f3.getDenominator());
    }

    @Test
    void testConstructorExceptions() {
        //denominator is zero
        assertThrows(IllegalArgumentException.class, () -> new FractionImpl(2, 0));
    }

    @Test
    void testSetters() {
        f12.setNumerator(5);
        assertEquals(5, f12.getNumerator());

        f12.setDenominator(10);
        assertEquals(2, f12.getDenominator());

        assertThrows(IllegalArgumentException.class, () -> f12.setDenominator(0));
        assertThrows(IllegalArgumentException.class, () -> f12.setDenominator(-5));
    }

    @Test
    void testToString() {
        Fraction f = new FractionImpl(24, 16);
        assertEquals("3 / 2", f.toString());

        Fraction f1 = new FractionImpl(0, 5);
        assertEquals("0 / 1", f1.toString());
    }

    @Test
    void testToDouble() {
        assertEquals(0.5, f12.toDouble(), 0.001);
        assertEquals(-0.5, fNeg12.toDouble(), 0.001);
    }

    @Test
    void testReciprocal() {
        Fraction f = new FractionImpl(1, 2);
        Fraction r = f.reciprocal();
        assertEquals("2 / 1", r.toString());

        Fraction zeroF = new FractionImpl(0, 1);
        assertThrows(IllegalArgumentException.class, zeroF::reciprocal);
    }

    @Test
    void testAdd() {
        Fraction res1 = f12.add(f12);
        assertEquals("1 / 1", res1.toString());

        Fraction res2 = f12.add(new FractionImpl(1, 3));
        assertEquals("5 / 6", res2.toString());

        Fraction res3 = f12.add(fNeg12);
        assertEquals("0 / 1", res3.toString());

        Fraction largeA = new FractionImpl(Integer.MAX_VALUE/2, 1);
        Fraction largeB = new FractionImpl(Integer.MAX_VALUE/2, 1);
        Fraction result = largeA.add(largeB);
        assertTrue(result.toDouble() > 0);


    }

    @Test
    void testCompareTo() {
        assertTrue(f12.compareTo(fNeg12) > 0);
        assertTrue(fNeg12.compareTo(f12) < 0);
        assertEquals(0, f12.compareTo(new FractionImpl(2, 4)));

        Fraction large1 = new FractionImpl(1000000, 3);
        Fraction large2 = new FractionImpl(1000001, 3);
        assertTrue(large1.compareTo(large2) < 0);
    }
}