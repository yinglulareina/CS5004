package problem2;

/**
 * A singly-linked list node that holds a single {@code String} value
 * and a reference to the next node in the chain.
 *
 * <p>This class is package-private and intended solely as an internal
 * building block for {@link LinkedListOfStrings}. External code should
 * interact only with the {@link IListOfStrings} interface.</p>
 */
class Node {

    /** The string data stored in this node. */
    String value;

    /** Reference to the next node; {@code null} if this is the tail. */
    Node next;

    /**
     * Constructs a Node with the given value and no successor.
     *
     * @param value the string to store; should not be {@code null}
     *              (enforced by {@link LinkedListOfStrings})
     */
    Node(String value) {
        this.value = value;
        this.next  = null;
    }
}