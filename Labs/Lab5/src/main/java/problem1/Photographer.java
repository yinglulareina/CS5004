package problem1;

/**
 * Represents a Photographer in the Seattle Digital Index.
 *
 * <p>A Photographer is a {@link VisualArtist} whose photographic work is
 * displayed in exhibits. The {@code exhibits} field is inherited from
 * {@link VisualArtist}.</p>
 */
public class Photographer extends VisualArtist {

    /**
     * Constructs a Photographer with all required fields.
     *
     * @param name     the photographer's name; must not be {@code null}
     * @param age      the photographer's age; must be in [0, 128]
     * @param genres   the photographer's genres; must not be {@code null}
     * @param awards   the photographer's awards; must not be {@code null}
     * @param exhibits exhibits where this photographer's work has been shown; must not be {@code null}
     */
    public Photographer(Name name, int age, String[] genres, String[] awards, String[] exhibits) {
        super(name, age, genres, awards, exhibits);
    }
}