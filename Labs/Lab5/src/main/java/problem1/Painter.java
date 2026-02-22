package problem1;

/**
 * Represents a Painter in the Seattle Digital Index.
 *
 * <p>A Painter is a {@link VisualArtist} whose artwork is displayed in
 * exhibits. The {@code exhibits} field is inherited from {@link VisualArtist}.</p>
 */
public class Painter extends VisualArtist {

    /**
     * Constructs a Painter with all required fields.
     *
     * @param name     the painter's name; must not be {@code null}
     * @param age      the painter's age; must be in [0, 128]
     * @param genres   the painter's genres; must not be {@code null}
     * @param awards   the painter's awards; must not be {@code null}
     * @param exhibits exhibits where this painter's work has been shown; must not be {@code null}
     */
    public Painter(Name name, int age, String[] genres, String[] awards, String[] exhibits) {
        super(name, age, genres, awards, exhibits);
    }
}