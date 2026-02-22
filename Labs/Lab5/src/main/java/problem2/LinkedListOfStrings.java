package problem2;

/**
 * A singly-linked list implementation of the {@link IListOfStrings} ADT.
 *
 * <p>Internally, the list is maintained as a chain of {@link Node} objects.
 * The head points to the first element; the tail node's {@code next} is
 * {@code null}. An empty list is represented by a {@code null} head.</p>
 *
 * <p>All mutating operations ({@link #addFront}) modify the list in-place,
 * while read-only operations that return a new list
 * ({@link #filterLargerThan}, {@link #removeDuplicates}) never modify
 * the receiver and always return a fresh {@code LinkedListOfStrings}.</p>
 *
 * Complexity notes:
 * <ul>
 *   <li>{@code isEmpty}, {@code size} — O(n) for size (no cached counter)</li>
 *   <li>{@code contains}, {@code containsAll} — O(n) and O(n·m)</li>
 *   <li>{@code filterLargerThan}, {@code removeDuplicates} — O(n²) worst case</li>
 *   <li>{@code hasDuplicates} — O(n²) worst case</li>
 * </ul>
 */
public class LinkedListOfStrings implements IListOfStrings {

    /** The head (first) node of the linked list; {@code null} when empty. */
    private Node head;

    /**
     * Constructs an empty {@code LinkedListOfStrings}.
     */
    public LinkedListOfStrings() {
        this.head = null;
    }

    // -------------------------------------------------------------------------
    // IListOfStrings implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * <p>Runs in O(1) time.</p>
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Traverses the entire list; runs in O(n) time.</p>
     */
    @Override
    public int size() {
        int count = 0;
        Node cur = head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Performs a linear scan; runs in O(n) time.</p>
     *
     * @throws IllegalArgumentException if {@code s} is {@code null}
     */
    @Override
    public boolean contains(String s) {
        if (s == null) throw new IllegalArgumentException("Search string must not be null.");
        Node cur = head;
        while (cur != null) {
            if (cur.value.equals(s)) return true;
            cur = cur.next;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * <p>For each element in {@code other}, calls {@link #contains} on {@code this}.
     * Runs in O(n·m) time where n = this.size(), m = other.size().</p>
     *
     * @throws IllegalArgumentException if {@code other} is {@code null}
     */
    @Override
    public boolean containsAll(IListOfStrings other) {
        if (other == null) throw new IllegalArgumentException("Other list must not be null.");
        // Walk the other list by casting to LinkedListOfStrings for internal access,
        // or iterate via a helper approach using a temporary list.
        if (!(other instanceof LinkedListOfStrings))
            throw new IllegalArgumentException("Incompatible IListOfStrings implementation.");

        Node cur = ((LinkedListOfStrings) other).head;
        while (cur != null) {
            if (!this.contains(cur.value)) return false;
            cur = cur.next;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Creates a new {@code LinkedListOfStrings} by traversing this list in
     * reverse (to preserve front-to-back order) and adding elements whose
     * length is ≤ {@code maxLength}. Runs in O(n) time.</p>
     *
     * @throws IllegalArgumentException if {@code maxLength} is negative
     */
    @Override
    public IListOfStrings filterLargerThan(int maxLength) {
        if (maxLength < 0) throw new IllegalArgumentException("maxLength must be non-negative.");

        // Collect qualifying values in order, then build the result list.
        // We use a two-pass approach: gather into a temp array, then addFront in reverse.
        int n = size();
        String[] buf = new String[n];
        int idx = 0;
        Node cur = head;
        while (cur != null) {
            if (cur.value.length() <= maxLength) buf[idx++] = cur.value;
            cur = cur.next;
        }

        LinkedListOfStrings result = new LinkedListOfStrings();
        for (int i = idx - 1; i >= 0; i--) {
            result.addFront(buf[i]);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * <p>For each element, scans all subsequent elements to find a match.
     * Runs in O(n²) time in the worst case.</p>
     */
    @Override
    public boolean hasDuplicates() {
        Node outer = head;
        while (outer != null) {
            Node inner = outer.next;
            while (inner != null) {
                if (outer.value.equals(inner.value)) return true;
                inner = inner.next;
            }
            outer = outer.next;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Traverses the list once and keeps only the first occurrence of each
     * unique string. Uses a helper {@code LinkedListOfStrings} as a "seen" set.
     * Preserves original order of first occurrences. Runs in O(n²) time.</p>
     */
    @Override
    public IListOfStrings removeDuplicates() {
        LinkedListOfStrings seen   = new LinkedListOfStrings();
        LinkedListOfStrings result = new LinkedListOfStrings();

        // Collect unique values in order using the buf trick.
        int n = size();
        String[] buf = new String[n];
        int idx = 0;
        Node cur = head;
        while (cur != null) {
            if (!seen.contains(cur.value)) {
                seen.addFront(cur.value);
                buf[idx++] = cur.value;
            }
            cur = cur.next;
        }

        // Rebuild in original order.
        for (int i = idx - 1; i >= 0; i--) {
            result.addFront(buf[i]);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Prepends the given string to the front of the list in O(1) time.</p>
     *
     * @throws IllegalArgumentException if {@code s} is {@code null}
     */
    @Override
    public void addFront(String s) {
        if (s == null) throw new IllegalArgumentException("Element must not be null.");
        Node node = new Node(s);
        node.next = head;
        head = node;
    }

    // -------------------------------------------------------------------------
    // Utility
    // -------------------------------------------------------------------------

    /**
     * Appends the given string to the back (tail) of the list.
     *
     * <p>Useful for building lists in natural order without reversals.
     * Runs in O(n) time.</p>
     *
     * @param s the string to append; must not be {@code null}
     * @throws IllegalArgumentException if {@code s} is {@code null}
     */
    public void addBack(String s) {
        if (s == null) throw new IllegalArgumentException("Element must not be null.");
        Node node = new Node(s);
        if (head == null) {
            head = node;
            return;
        }
        Node cur = head;
        while (cur.next != null) cur = cur.next;
        cur.next = node;
    }

    /**
     * Returns a string representation of this list in the format
     * {@code [element1, element2, ...]}, or {@code []} for an empty list.
     *
     * @return a formatted string showing all elements in front-to-back order
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node cur = head;
        while (cur != null) {
            sb.append(cur.value);
            if (cur.next != null) sb.append(", ");
            cur = cur.next;
        }
        sb.append("]");
        return sb.toString();
    }
}