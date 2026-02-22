package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Artist hierarchy (Problem 1).
 *
 * <p>Covers construction, {@link Artist#receiveAward(String)}, defensive copies,
 * and exception-throwing behavior for invalid inputs.</p>
 */
public class ArtistTest {

    private Name name;
    private String[] genres;
    private String[] awards;
    private String[] empty;

    @BeforeEach
    void setUp() {
        name   = new Name("Jimi", "Hendrix");
        genres = new String[]{"Rock", "Blues"};
        awards = new String[]{"Grammy"};
        empty  = new String[]{};
    }

    // ── Musician ──────────────────────────────────────────────────────────────

    /**
     * Verifies that a Musician is constructed with correct field values.
     */
    @Test
    void testMusicianConstruction() {
        Musician m = new Musician(name, 27, genres, awards, "Reprise Records", "Electric Ladyland");
        assertEquals("Jimi",   m.getName().getFirstName());
        assertEquals("Hendrix",m.getName().getLastName());
        assertEquals(27,       m.getAge());
        assertEquals("Reprise Records",  m.getRecordingCompany());
        assertEquals("Electric Ladyland",m.getLastRecordAlbum());
    }

    /**
     * Verifies that receiveAward appends the award to the existing array.
     */
    @Test
    void testReceiveAward() {
        Musician m = new Musician(name, 27, genres, awards, "Reprise", "Band of Gypsys");
        m.receiveAward("Rock and Roll Hall of Fame");
        String[] updated = m.getAwards();
        assertEquals(2, updated.length);
        assertEquals("Grammy",              updated[0]);
        assertEquals("Rock and Roll Hall of Fame", updated[1]);
    }

    /**
     * Verifies that getAwards returns a defensive copy, not the internal array.
     * Mutating the returned array must not affect the artist's internal state.
     */
    @Test
    void testGetAwardsDefensiveCopy() {
        Musician m = new Musician(name, 27, genres, new String[]{"Grammy"}, "Reprise", "Are You Experienced");
        String[] copy = m.getAwards();
        copy[0] = "TAMPERED"; // mutate the copy
        // internal state should still hold the original value
        assertEquals("Grammy", m.getAwards()[0]);
        assertEquals(1, m.getAwards().length);
    }

    /**
     * Verifies that constructing a Musician with an invalid age throws an exception.
     */
    @Test
    void testInvalidAgeThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Musician(name, -1, genres, awards, "Label", "Album"));
        assertThrows(IllegalArgumentException.class,
                () -> new Musician(name, 129, genres, awards, "Label", "Album"));
    }

    /**
     * Verifies that passing null to receiveAward throws an exception.
     */
    @Test
    void testReceiveAwardNullThrows() {
        Musician m = new Musician(name, 27, genres, awards, "Reprise", "Album");
        assertThrows(IllegalArgumentException.class, () -> m.receiveAward(null));
    }

    // ── Actor ─────────────────────────────────────────────────────────────────

    /**
     * Verifies that an Actor is constructed correctly with screen-media fields.
     */
    @Test
    void testActorConstruction() {
        Actor a = new Actor(name, 30, genres, awards,
                new String[]{"Hamlet"},
                new String[]{"The Crown"},
                new String[]{"Short Film X"});
        assertArrayEquals(new String[]{"Hamlet"},      a.getMovies());
        assertArrayEquals(new String[]{"The Crown"},   a.getSeries());
        assertArrayEquals(new String[]{"Short Film X"},a.getOtherMultimedia());
    }

    // ── Painter ───────────────────────────────────────────────────────────────

    /**
     * Verifies that a Painter is constructed correctly with exhibit information.
     */
    @Test
    void testPainterConstruction() {
        Painter p = new Painter(name, 45, genres, awards,
                new String[]{"Seattle Art Museum", "MOMA"});
        assertArrayEquals(new String[]{"Seattle Art Museum", "MOMA"}, p.getExhibits());
    }

    // ── Poet ──────────────────────────────────────────────────────────────────

    /**
     * Verifies that a Poet is constructed correctly with publishing details.
     */
    @Test
    void testPoetConstruction() {
        Poet p = new Poet(name, 60, genres, awards, "Knopf", "Leaves of Fire");
        assertEquals("Knopf",         p.getPublishingCompany());
        assertEquals("Leaves of Fire",p.getLastPublishedCollection());
    }
}