package problem1;

import java.util.Arrays;

/**
 * Abstract base class representing a generic Artist in the Seattle Digital Index.
 *
 * <p>All artist subtypes share a common identity defined here: name, age, genres,
 * and awards. The {@link #receiveAward(String)} method provides a shared implementation
 * for appending a new award to the artist's current award list.</p>
 *
 * <p>This class enforces the invariant that age must be in the range [0, 128].
 * Subclasses are expected to extend this class and add type-specific fields.</p>
 */
public abstract class Artist {

    /** The artist's full name. */
    private final Name name;

    /**
     * The artist's age. Must be in the range [0, 128].
     */
    private final int age;

    /** The genres associated with this artist's work. */
    private final String[] genres;

    /** The awards this artist has received. */
    private String[] awards;

    /**
     * Constructs an Artist with the given name, age, genres, and awards.
     *
     * @param name   the artist's name (first and last); must not be {@code null}
     * @param age    the artist's age; must be in [0, 128]
     * @param genres the genres this artist works in; must not be {@code null}
     * @param awards the awards this artist has received; must not be {@code null}
     * @throws IllegalArgumentException if age is out of range [0, 128]
     * @throws IllegalArgumentException if name, genres, or awards is {@code null}
     */
    public Artist(Name name, int age, String[] genres, String[] awards) {
        if (name == null)   throw new IllegalArgumentException("Name must not be null.");
        if (genres == null) throw new IllegalArgumentException("Genres must not be null.");
        if (awards == null) throw new IllegalArgumentException("Awards must not be null.");
        if (age < 0 || age > 128)
            throw new IllegalArgumentException("Age must be in the range [0, 128].");

        this.name   = name;
        this.age    = age;
        this.genres = Arrays.copyOf(genres, genres.length);
        this.awards = Arrays.copyOf(awards, awards.length);
    }

    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------

    /**
     * Returns the artist's name.
     *
     * @return the {@link Name} of this artist
     */
    public Name getName() { return name; }

    /**
     * Returns the artist's age.
     *
     * @return age as an integer in [0, 128]
     */
    public int getAge() { return age; }

    /**
     * Returns a copy of the artist's genres array.
     *
     * @return a defensive copy of the genres array
     */
    public String[] getGenres() { return Arrays.copyOf(genres, genres.length); }

    /**
     * Returns a copy of the artist's awards array.
     *
     * @return a defensive copy of the awards array
     */
    public String[] getAwards() { return Arrays.copyOf(awards, awards.length); }

    // -------------------------------------------------------------------------
    // Behavior
    // -------------------------------------------------------------------------

    /**
     * Adds a new award to this artist's list of awards.
     *
     * <p>A new array is created that contains all existing awards plus the new one
     * appended at the end. The original array is replaced.</p>
     *
     * @param award the name of the award to add; must not be {@code null}
     * @throws IllegalArgumentException if {@code award} is {@code null}
     */
    public void receiveAward(String award) {
        if (award == null) throw new IllegalArgumentException("Award must not be null.");
        String[] updated = Arrays.copyOf(awards, awards.length + 1);
        updated[awards.length] = award;
        this.awards = updated;
    }

    /**
     * Returns a string representation of this artist, including name, age,
     * genres, and awards.
     *
     * @return a formatted string describing this artist
     */
    @Override
    public String toString() {
        return String.format(
                "%s | Name: %s | Age: %d | Genres: %s | Awards: %s",
                getClass().getSimpleName(),
                name,
                age,
                Arrays.toString(genres),
                Arrays.toString(awards)
        );
    }
}