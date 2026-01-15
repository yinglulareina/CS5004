/**
 * Represents a Person with their details--name, email and physical address
 * @author Aida
 */
public class Person {
    private String name;
    private String email;
    private String address;
    /**
     Creates a new person given the person's name, email and address as strings.
     * @param name the person's name
     * @param email the person's email address
     * @param address the person’s physical address
     */
    public Person(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address; }
    /**
     * @return the name
     */
    public String getName() { return this.name; }
    /**
     * @return the email
     */
    public String getEmail() { return this.email; }
    /**
     * @return the address
     */
    public String getAddress() {return this.address; }}