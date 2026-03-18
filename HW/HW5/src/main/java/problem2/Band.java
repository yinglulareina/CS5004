package problem2;

import java.util.Collection;
import java.util.Objects;

/**
 * Represents a Band consisting of Recording Artists.
 */
public class Band extends Creator {
    private String name;
    private Collection<RecordingArtist> members;

    public Band(String name, Collection<RecordingArtist> members) {
        this.name = name;
        this.members = members;
    }

    public String getName() { return name; }
    public Collection<RecordingArtist> getMembers() { return members; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Band band = (Band) o;
        return Objects.equals(name, band.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}