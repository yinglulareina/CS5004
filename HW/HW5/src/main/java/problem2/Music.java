package problem2;

/**
 * Represents Music created by a RecordingArtist or a Band.
 */
public class Music extends Item {
    public Music(String title, int year, Creator creator) {
        super(title, year, creator);
    }
}