package problem1;

import java.util.Arrays;

/**
 * Abstract intermediate class representing artists whose work is shown in
 * physical or virtual exhibits: painters and photographers.
 *
 * <p>This class serves as the shared superclass for {@link Painter} and
 * {@link Photographer}, capturing their common {@code exhibits} field and
 * avoiding duplication across those concrete subclasses.</p>
 *
 * Relationship in the hierarchy:
 * <pre>
 *   Artist
 *     └── VisualArtist  (abstract)
 *           ├── Painter
 *           └── Photographer
 * </pre>
 *
 * @author CS5004 Lab 5
 * @version 1.0
 */
public abstract class VisualArtist extends Artist {

    /** All exhibits where this artist's work has been shown. */
    private String[] exhibits;

    /**
     * Constructs a VisualArtist with all base fields plus exhibit history.
     *
     * @param name     the artist's name; must not be {@code null}
     * @param age      the artist's age; must be in [0, 128]
     * @param genres   the artist's genres; must not be {@code null}
     * @param awards   the artist's awards; must not be {@code null}
     * @param exhibits exhibits where this artist's work was shown; must not be {@code null}
     * @throws IllegalArgumentException if exhibits is {@code null}
     */
    public VisualArtist(Name name, int age, String[] genres, String[] awards, String[] exhibits) {
        super(name, age, genres, awards);
        if (exhibits == null) throw new IllegalArgumentException("Exhibits must not be null.");
        this.exhibits = Arrays.copyOf(exhibits, exhibits.length);
    }

    /**
     * Returns a copy of the exhibits array.
     *
     * @return a defensive copy of the exhibits this artist's work has appeared in
     */
    public String[] getExhibits() { return Arrays.copyOf(exhibits, exhibits.length); }

    /**
     * Returns a string representation including exhibit history in addition
     * to the base {@link Artist} information.
     *
     * @return a formatted string describing this visual artist
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" | Exhibits: %s", Arrays.toString(exhibits));
    }
}