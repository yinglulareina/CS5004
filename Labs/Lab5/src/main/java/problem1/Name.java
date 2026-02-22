package problem1;

/**
 * Immutable value object representing an artist's full name.
 *
 * <p>Encapsulates a first name and a last name as separate fields,
 * providing a clean and reusable abstraction for name data across
 * all {@link Artist} subtypes.</p>
 */
public class Name {

    /** The artist's first name. */
    private final String firstName;

    /** The artist's last name. */
    private final String lastName;

    /**
     * Constructs a Name with the given first and last name.
     *
     * @param firstName the artist's first name; must not be {@code null} or blank
     * @param lastName  the artist's last name;  must not be {@code null} or blank
     * @throws IllegalArgumentException if either name is {@code null} or blank
     */
    public Name(String firstName, String lastName) {
        if (firstName == null || firstName.isBlank())
            throw new IllegalArgumentException("First name must not be null or blank.");
        if (lastName == null || lastName.isBlank())
            throw new IllegalArgumentException("Last name must not be null or blank.");
        this.firstName = firstName;
        this.lastName  = lastName;
    }

    /**
     * Returns the artist's first name.
     *
     * @return the first name
     */
    public String getFirstName() { return firstName; }

    /**
     * Returns the artist's last name.
     *
     * @return the last name
     */
    public String getLastName() { return lastName; }

    /**
     * Returns the full name in "FirstName LastName" format.
     *
     * @return a formatted full name string
     */
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}