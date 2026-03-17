package problem2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem2Test {
    public static void main(String[] args) {
        // 1. Setup Creators
        Author author = new Author("J.K.", "Rowling");
        RecordingArtist artist1 = new RecordingArtist("Freddie", "Mercury");
        RecordingArtist artist2 = new RecordingArtist("Brian", "May");

        // Create a Band
        Band queen = new Band("Queen", new ArrayList<>(Arrays.asList(artist1, artist2)));

        // 2. Setup Items
        Book book = new Book("Harry Potter", 1997, author);
        Music soloAlbum = new Music("Mr. Bad Guy", 1985, artist1);
        Music bandAlbum = new Music("A Night at the Opera", 1975, queen);

        // 3. Setup Catalog
        Catalog catalog = new Catalog();
        catalog.addItem(book);
        catalog.addItem(soloAlbum);
        catalog.addItem(bandAlbum);

        System.out.println("--- Library Catalog Test ---");

        // Test 1: Keyword search (Case-insensitive)
        // Should find "Harry Potter" and "A Night at the Opera"
        System.out.println("\nSearch for keyword 'A':");
        for (Item item : catalog.search("a")) {
            System.out.println("- Found: " + item.getTitle());
        }

        // Test 2: Search by Author
        // Should only find the book
        System.out.println("\nSearch for Author 'J.K. Rowling':");
        for (Item item : catalog.search(author)) {
            System.out.println("- Found: " + item.getTitle());
        }

        // Test 3: Search by Recording Artist (Solo)
        // Should find "Mr. Bad Guy"
        System.out.println("\nSearch for Artist 'Freddie Mercury' (Solo):");
        for (Item item : catalog.search(artist1)) {
            System.out.println("- Found: " + item.getTitle());
        }

        // Test 4: Search by Recording Artist (As Band Member)
        // Should find "A Night at the Opera" because Brian May is in Queen
        System.out.println("\nSearch for Artist 'Brian May' (Band Member):");
        for (Item item : catalog.search(artist2)) {
            System.out.println("- Found: " + item.getTitle());
        }
    }
}