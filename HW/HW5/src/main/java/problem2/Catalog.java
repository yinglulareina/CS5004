package problem2;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores items and provides overloaded search functionality.
 */
public class Catalog {
    private List<Item> items;

    public Catalog() { this.items = new ArrayList<>(); }
    public Catalog(List<Item> items) { this.items = new ArrayList<>(items); }

    public void addItem(Item item) { items.add(item); }
    public void removeItem(Item item) { items.remove(item); }

    /**
     * Search by keyword in title (Case-insensitive).
     */
    public List<Item> search(String keyword) {
        List<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Search for books by an exact Author match.
     */
    public List<Item> search(Author author) {
        List<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Book && item.getCreator().equals(author)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Search for music by Recording Artist (Solo or Band member).
     */
    public List<Item> search(RecordingArtist artist) {
        List<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Music) {
                Creator creator = item.getCreator();
                if (creator.equals(artist)) {
                    result.add(item);
                } else if (creator instanceof Band) {
                    if (((Band) creator).getMembers().contains(artist)) {
                        result.add(item);
                    }
                }
            }
        }
        return result;
    }
}