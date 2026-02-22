package problem1;

/**
 * Represents a Poet in the Seattle Digital Index.
 *
 * <p>A Poet extends {@link Artist} directly and additionally tracks
 * their publishing company and the title of their most recently published
 * poetry collection.</p>
 */
public class Poet extends Artist {

    /** The publishing company this poet is associated with. */
    private String publishingCompany;

    /** The title of this poet's most recently published collection. */
    private String lastPublishedCollection;

    /**
     * Constructs a Poet with all required fields.
     *
     * @param name                    the poet's name; must not be {@code null}
     * @param age                     the poet's age; must be in [0, 128]
     * @param genres                  the poet's genres; must not be {@code null}
     * @param awards                  the poet's awards; must not be {@code null}
     * @param publishingCompany       the publishing company; must not be {@code null}
     * @param lastPublishedCollection the title of the latest poetry collection; must not be {@code null}
     * @throws IllegalArgumentException if publishingCompany or lastPublishedCollection is {@code null}
     */
    public Poet(Name name, int age, String[] genres, String[] awards,
                String publishingCompany, String lastPublishedCollection) {
        super(name, age, genres, awards);
        if (publishingCompany == null)       throw new IllegalArgumentException("Publishing company must not be null.");
        if (lastPublishedCollection == null) throw new IllegalArgumentException("Last published collection must not be null.");
        this.publishingCompany       = publishingCompany;
        this.lastPublishedCollection = lastPublishedCollection;
    }

    /**
     * Returns the name of the poet's publishing company.
     *
     * @return the publishing company name
     */
    public String getPublishingCompany() { return publishingCompany; }

    /**
     * Returns the title of the poet's latest published collection.
     *
     * @return the title of the most recently published collection
     */
    public String getLastPublishedCollection() { return lastPublishedCollection; }

    /**
     * Returns a string representation of this Poet including publishing
     * company and latest collection in addition to base {@link Artist} information.
     *
     * @return a formatted string describing this poet
     */
    @Override
    public String toString() {
        return super.toString()
                + String.format(" | Publishing Company: %s | Last Collection: %s",
                publishingCompany, lastPublishedCollection);
    }
}