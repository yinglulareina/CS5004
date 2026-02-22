package problem1;

/**
 * Represents a Dancer in the Seattle Digital Index.
 *
 * <p>A Dancer is a {@link ScreenArtist} whose performances may appear in
 * movies, TV series, and other multimedia productions. All screen-media
 * fields are inherited from {@link ScreenArtist}.</p>
 */
public class Dancer extends ScreenArtist {

    /**
     * Constructs a Dancer with all required fields.
     *
     * @param name            the dancer's name; must not be {@code null}
     * @param age             the dancer's age; must be in [0, 128]
     * @param genres          the dancer's genres; must not be {@code null}
     * @param awards          the dancer's awards; must not be {@code null}
     * @param movies          movies the dancer has appeared in; must not be {@code null}
     * @param series          TV series the dancer has appeared in; must not be {@code null}
     * @param otherMultimedia other multimedia the dancer has appeared in; must not be {@code null}
     */
    public Dancer(Name name, int age, String[] genres, String[] awards,
                  String[] movies, String[] series, String[] otherMultimedia) {
        super(name, age, genres, awards, movies, series, otherMultimedia);
    }
}