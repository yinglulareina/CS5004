package problem1;

import java.util.Arrays;

/**
 * Abstract intermediate class representing artists whose work appears
 * in screen-based media: movies, TV series, and other multimedia content.
 *
 * <p>This class serves as the shared superclass for {@link Actor},
 * {@link Dancer}, and {@link Filmmaker}. It captures the common fields
 * and behavior for tracking screen-based credits, avoiding duplication
 * across those concrete subclasses.</p>
 *
 * Relationship in the hierarchy:
 * <pre>
 *   Artist
 *     └── ScreenArtist  (abstract)
 *           ├── Actor
 *           ├── Dancer
 *           └── Filmmaker
 * </pre>
 *
 * @author CS5004 Lab 5
 * @version 1.0
 */
public abstract class ScreenArtist extends Artist {

    /** All movies this artist has worked on. */
    private String[] movies;

    /** All TV series this artist has worked on. */
    private String[] series;

    /** All other multimedia content this artist has worked on. */
    private String[] otherMultimedia;

    /**
     * Constructs a ScreenArtist with all base fields plus screen-media credits.
     *
     * @param name            the artist's name; must not be {@code null}
     * @param age             the artist's age; must be in [0, 128]
     * @param genres          the artist's genres; must not be {@code null}
     * @param awards          the artist's awards; must not be {@code null}
     * @param movies          movies this artist worked on; must not be {@code null}
     * @param series          TV series this artist worked on; must not be {@code null}
     * @param otherMultimedia other multimedia this artist worked on; must not be {@code null}
     * @throws IllegalArgumentException if movies, series, or otherMultimedia is {@code null}
     */
    public ScreenArtist(Name name, int age, String[] genres, String[] awards,
                        String[] movies, String[] series, String[] otherMultimedia) {
        super(name, age, genres, awards);
        if (movies == null)          throw new IllegalArgumentException("Movies must not be null.");
        if (series == null)          throw new IllegalArgumentException("Series must not be null.");
        if (otherMultimedia == null) throw new IllegalArgumentException("OtherMultimedia must not be null.");

        this.movies          = Arrays.copyOf(movies, movies.length);
        this.series          = Arrays.copyOf(series, series.length);
        this.otherMultimedia = Arrays.copyOf(otherMultimedia, otherMultimedia.length);
    }

    /**
     * Returns a copy of the movies array.
     *
     * @return a defensive copy of the movies this artist has worked on
     */
    public String[] getMovies() { return Arrays.copyOf(movies, movies.length); }

    /**
     * Returns a copy of the TV series array.
     *
     * @return a defensive copy of the TV series this artist has worked on
     */
    public String[] getSeries() { return Arrays.copyOf(series, series.length); }

    /**
     * Returns a copy of the other multimedia array.
     *
     * @return a defensive copy of other multimedia content this artist has worked on
     */
    public String[] getOtherMultimedia() { return Arrays.copyOf(otherMultimedia, otherMultimedia.length); }

    /**
     * Returns a string representation including screen-media credits in addition
     * to the base {@link Artist} information.
     *
     * @return a formatted string describing this screen artist
     */
    @Override
    public String toString() {
        return super.toString()
                + String.format(" | Movies: %s | Series: %s | Other: %s",
                Arrays.toString(movies),
                Arrays.toString(series),
                Arrays.toString(otherMultimedia));
    }
}