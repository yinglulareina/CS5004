package problem2;

/**
 * ADT (Abstract Data Type) interface defining the contract for a List of Strings.
 *
 * <p>This interface specifies all public behaviors that any implementation of a
 * List of Strings must provide. The contract is implementation-agnostic: callers
 * depend only on this interface, not on any concrete class.</p>
 *
 * <p>An empty list is a valid state. All methods must handle the empty list
 * without throwing unexpected exceptions.</p>
 */
public interface IListOfStrings {

    /**
     * Checks whether this list contains no elements.
     *
     * @return {@code true} if the list has no elements; {@code false} otherwise
     */
    boolean isEmpty();

    /**
     * Returns the total number of elements in this list.
     *
     * @return the number of elements as a non-negative integer
     */
    int size();

    /**
     * Checks whether the specified string is present in this list.
     *
     * @param s the string to search for; must not be {@code null}
     * @return {@code true} if {@code s} is found in the list; {@code false} otherwise
     * @throws IllegalArgumentException if {@code s} is {@code null}
     */
    boolean contains(String s);

    /**
     * Checks whether every element of the given list is also present in this list.
     *
     * <p>Returns {@code true} vacuously if {@code other} is empty, because
     * every element of an empty set is trivially contained in any set.</p>
     *
     * @param other the list of strings to check for; must not be {@code null}
     * @return {@code true} if all elements of {@code other} are in this list;
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code other} is {@code null}
     */
    boolean containsAll(IListOfStrings other);

    /**
     * Returns a new list containing only the elements whose length does not
     * exceed the given maximum length.
     *
     * <p>Elements with {@code length > maxLength} are excluded. The original
     * list is not modified.</p>
     *
     * @param maxLength the maximum allowed string length (inclusive)
     * @return a new {@code IListOfStrings} with all oversized elements removed
     * @throws IllegalArgumentException if {@code maxLength} is negative
     */
    IListOfStrings filterLargerThan(int maxLength);

    /**
     * Checks whether this list contains at least one duplicate element.
     *
     * <p>Two elements are considered duplicates if they are equal by
     * {@link String#equals(Object)}.</p>
     *
     * @return {@code true} if any element appears more than once; {@code false} otherwise
     */
    boolean hasDuplicates();

    /**
     * Returns a new list with all duplicate elements removed, retaining only
     * the first occurrence of each unique string.
     *
     * <p>The original list is not modified. Relative order of first occurrences
     * is preserved.</p>
     *
     * @return a new {@code IListOfStrings} with duplicates removed
     */
    IListOfStrings removeDuplicates();

    /**
     * Adds a string to the front of this list.
     *
     * @param s the string to add; must not be {@code null}
     * @throws IllegalArgumentException if {@code s} is {@code null}
     */
    void addFront(String s);

    /**
     * Returns a string representation of this list, e.g., {@code [a, b, c]}.
     *
     * @return a formatted string showing all elements in order
     */
    String toString();
}