package problem1;

/**
 * Represents an Actor in the Seattle Digital Index.
 *
 * <p>An Actor is a {@link ScreenArtist} who performs in movies, TV series,
 * and other multimedia content. All screen-media fields are inherited from
 * {@link ScreenArtist}; this class exists as a concrete, instantiable type.</p>
 *
 */
public class Actor extends ScreenArtist {

    /**
     * Constructs an Actor with all required fields.
     *
     * @param name            the actor's name; must not be {@code null}
     * @param age             the actor's age; must be in [0, 128]
     * @param genres          the actor's genres; must not be {@code null}
     * @param awards          the actor's awards; must not be {@code null}
     * @param movies          movies the actor has appeared in; must not be {@code null}
     * @param series          TV series the actor has appeared in; must not be {@code null}
     * @param otherMultimedia other multimedia the actor has appeared in; must not be {@code null}
     */
    public Actor(Name name, int age, String[] genres, String[] awards,
                 String[] movies, String[] series, String[] otherMultimedia) {
        super(name, age, genres, awards, movies, series, otherMultimedia);
    }
}