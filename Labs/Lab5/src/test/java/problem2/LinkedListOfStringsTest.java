package problem2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link LinkedListOfStrings} (Problem 2).
 *
 * <p>Covers all seven ADT operations defined in {@link IListOfStrings},
 * including boundary conditions (empty list, single element, duplicates)
 * and exception-throwing behavior for invalid inputs.</p>
 */
public class LinkedListOfStringsTest {

    private LinkedListOfStrings list;

    @BeforeEach
    void setUp() {
        list = new LinkedListOfStrings();
    }

    // ── isEmpty ───────────────────────────────────────────────────────────────

    /** A freshly constructed list must be empty. */
    @Test
    void testIsEmptyOnNewList() {
        assertTrue(list.isEmpty());
    }

    /** A list with one element must not be empty. */
    @Test
    void testIsEmptyAfterAdd() {
        list.addBack("hello");
        assertFalse(list.isEmpty());
    }

    // ── size ──────────────────────────────────────────────────────────────────

    /** Size of an empty list is 0. */
    @Test
    void testSizeEmpty() {
        assertEquals(0, list.size());
    }

    /** Size reflects the number of elements added. */
    @Test
    void testSizeAfterAdds() {
        list.addBack("a"); list.addBack("b"); list.addBack("c");
        assertEquals(3, list.size());
    }

    // ── contains ──────────────────────────────────────────────────────────────

    /** contains returns false on an empty list. */
    @Test
    void testContainsOnEmptyList() {
        assertFalse(list.contains("x"));
    }

    /** contains returns true for a present element. */
    @Test
    void testContainsPresent() {
        list.addBack("apple"); list.addBack("banana");
        assertTrue(list.contains("apple"));
        assertTrue(list.contains("banana"));
    }

    /** contains returns false for an absent element. */
    @Test
    void testContainsAbsent() {
        list.addBack("apple");
        assertFalse(list.contains("cherry"));
    }

    /** contains throws when passed null. */
    @Test
    void testContainsNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> list.contains(null));
    }

    // ── containsAll ───────────────────────────────────────────────────────────

    /** containsAll returns true vacuously for an empty other list. */
    @Test
    void testContainsAllEmptyOther() {
        list.addBack("a");
        LinkedListOfStrings other = new LinkedListOfStrings();
        assertTrue(list.containsAll(other));
    }

    /** containsAll returns true when all elements of other are present. */
    @Test
    void testContainsAllAllPresent() {
        list.addBack("a"); list.addBack("b"); list.addBack("c");
        LinkedListOfStrings other = new LinkedListOfStrings();
        other.addBack("a"); other.addBack("c");
        assertTrue(list.containsAll(other));
    }

    /** containsAll returns false when at least one element is missing. */
    @Test
    void testContainsAllOneMissing() {
        list.addBack("a"); list.addBack("b");
        LinkedListOfStrings other = new LinkedListOfStrings();
        other.addBack("a"); other.addBack("z");
        assertFalse(list.containsAll(other));
    }

    // ── filterLargerThan ──────────────────────────────────────────────────────

    /** filterLargerThan on an empty list returns an empty list. */
    @Test
    void testFilterLargerThanEmpty() {
        IListOfStrings result = list.filterLargerThan(5);
        assertTrue(result.isEmpty());
    }

    /** Elements longer than maxLength are removed. */
    @Test
    void testFilterLargerThanRemovesLong() {
        list.addBack("hi");        // length 2
        list.addBack("hello");     // length 5
        list.addBack("goodbye");   // length 7
        IListOfStrings result = list.filterLargerThan(5);
        assertEquals(2, result.size());
        assertTrue(result.contains("hi"));
        assertTrue(result.contains("hello"));
        assertFalse(result.contains("goodbye"));
    }

    /** filterLargerThan with maxLength=0 keeps only empty strings. */
    @Test
    void testFilterLargerThanZero() {
        list.addBack(""); list.addBack("a");
        IListOfStrings result = list.filterLargerThan(0);
        assertEquals(1, result.size());
        assertTrue(result.contains(""));
    }

    /** filterLargerThan throws on negative maxLength. */
    @Test
    void testFilterLargerThanNegativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> list.filterLargerThan(-1));
    }

    // ── hasDuplicates ─────────────────────────────────────────────────────────

    /** hasDuplicates returns false for an empty list. */
    @Test
    void testHasDuplicatesEmpty() {
        assertFalse(list.hasDuplicates());
    }

    /** hasDuplicates returns false when all elements are unique. */
    @Test
    void testHasDuplicatesNone() {
        list.addBack("a"); list.addBack("b"); list.addBack("c");
        assertFalse(list.hasDuplicates());
    }

    /** hasDuplicates returns true when a duplicate exists. */
    @Test
    void testHasDuplicatesTrue() {
        list.addBack("a"); list.addBack("b"); list.addBack("a");
        assertTrue(list.hasDuplicates());
    }

    // ── removeDuplicates ──────────────────────────────────────────────────────

    /** removeDuplicates on an empty list returns an empty list. */
    @Test
    void testRemoveDuplicatesEmpty() {
        IListOfStrings result = list.removeDuplicates();
        assertTrue(result.isEmpty());
    }

    /** removeDuplicates keeps only the first occurrence, preserving order. */
    @Test
    void testRemoveDuplicatesPreservesOrder() {
        list.addBack("a"); list.addBack("b"); list.addBack("a"); list.addBack("c"); list.addBack("b");
        IListOfStrings result = list.removeDuplicates();
        assertEquals(3, result.size());
        assertEquals("[a, b, c]", result.toString());
    }

    /** removeDuplicates does not modify the original list. */
    @Test
    void testRemoveDuplicatesOriginalUnchanged() {
        list.addBack("x"); list.addBack("x");
        list.removeDuplicates();
        assertEquals(2, list.size());
    }

    // ── addFront / toString ───────────────────────────────────────────────────

    /** addFront places the element at position 0. */
    @Test
    void testAddFrontOrder() {
        list.addBack("b"); list.addFront("a");
        assertEquals("[a, b]", list.toString());
    }

    /** toString returns [] for an empty list. */
    @Test
    void testToStringEmpty() {
        assertEquals("[]", list.toString());
    }
}