package problem1;

/**
 * Represents a Musician in the Seattle Digital Index.
 *
 * <p>A Musician extends {@link Artist} directly and additionally tracks
 * their recording company and the title of their most recent album.</p>
 */
public class Musician extends Artist {

    /** The recording company this musician is signed to. */
    private String recordingCompany;

    /** The title of this musician's most recently recorded album. */
    private String lastRecordAlbum;

    /**
     * Constructs a Musician with all required fields.
     *
     * @param name             the musician's name; must not be {@code null}
     * @param age              the musician's age; must be in [0, 128]
     * @param genres           the musician's genres; must not be {@code null}
     * @param awards           the musician's awards; must not be {@code null}
     * @param recordingCompany the recording company; must not be {@code null}
     * @param lastRecordAlbum  the title of the latest album; must not be {@code null}
     * @throws IllegalArgumentException if recordingCompany or lastRecordAlbum is {@code null}
     */
    public Musician(Name name, int age, String[] genres, String[] awards,
                    String recordingCompany, String lastRecordAlbum) {
        super(name, age, genres, awards);
        if (recordingCompany == null) throw new IllegalArgumentException("Recording company must not be null.");
        if (lastRecordAlbum == null)  throw new IllegalArgumentException("Last record album must not be null.");
        this.recordingCompany = recordingCompany;
        this.lastRecordAlbum  = lastRecordAlbum;
    }

    /**
     * Returns the name of the musician's recording company.
     *
     * @return the recording company name
     */
    public String getRecordingCompany() { return recordingCompany; }

    /**
     * Returns the title of the musician's latest album.
     *
     * @return the title of the most recently recorded album
     */
    public String getLastRecordAlbum() { return lastRecordAlbum; }

    /**
     * Returns a string representation of this Musician including recording
     * company and latest album in addition to base {@link Artist} information.
     *
     * @return a formatted string describing this musician
     */
    @Override
    public String toString() {
        return super.toString()
                + String.format(" | Recording Company: %s | Last Album: %s",
                recordingCompany, lastRecordAlbum);
    }
}