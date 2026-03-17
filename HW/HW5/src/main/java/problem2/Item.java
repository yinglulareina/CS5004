package problem2;

/**
 * Base class for all library items (Books and Music)[cite: 38, 121].
 */
public abstract class Item {
    protected String title;
    protected int year;
    protected Creator creator;

    public Item(String title, int year, Creator creator) {
        this.title = title;
        this.year = year;
        this.creator = creator;
    }

    public String getTitle() { return title; }
    public Creator getCreator() { return creator; }
}