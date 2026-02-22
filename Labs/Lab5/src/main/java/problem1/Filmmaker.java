package problem1;

/**
 * Represents a Filmmaker in the Seattle Digital Index.
 *
 * <p>A Filmmaker is a {@link ScreenArtist} who directs, produces, or otherwise
 * works on movies, TV series, and other multimedia content. All screen-media
 * fields are inherited from {@link ScreenArtist}.</p>
 */
public class Filmmaker extends ScreenArtist {

    /**
     * Constructs a Filmmaker with all required fields.
     *
     * @param name            the filmmaker's name; must not be {@code null}
     * @param age             the filmmaker's age; must be in [0, 128]
     * @param genres          the filmmaker's genres; must not be {@code null}
     * @param awards          the filmmaker's awards; must not be {@code null}
     * @param movies          movies the filmmaker has worked on; must not be {@code null}
     * @param series          TV series the filmmaker has worked on; must not be {@code null}
     * @param otherMultimedia other multimedia the filmmaker has worked on; must not be {@code null}
     */
    public Filmmaker(Name name, int age, String[] genres, String[] awards,
                     String[] movies, String[] series, String[] otherMultimedia) {
        super(name, age, genres, awards, movies, series, otherMultimedia);
    }
}