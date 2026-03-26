import java.util.Objects;

/**
 * Represents a student with a first name, last name, student ID, and email address.
 *
 * <p>This class serves as the data model used by {@link StudentReader} to store
 * each student record read from {@code students.txt}.</p>
 *
 * @author Ying Lu
 */
public class Student {

    /** The student's first name. */
    private String firstName;

    /** The student's last name. */
    private String lastName;

    /** The student's unique ID number. */
    private int studentID;

    /** The student's email address. */
    private String email;

    /**
     * Constructs a Student with all required fields.
     *
     * @param firstName the student's first name; must not be null
     * @param lastName  the student's last name;  must not be null
     * @param studentID the student's ID number
     * @param email     the student's email address; must not be null
     * @throws IllegalArgumentException if any String argument is null
     */
    public Student(String firstName, String lastName, int studentID, String email) {
        if (firstName == null) throw new IllegalArgumentException("First name must not be null.");
        if (lastName  == null) throw new IllegalArgumentException("Last name must not be null.");
        if (email     == null) throw new IllegalArgumentException("Email must not be null.");
        this.firstName = firstName;
        this.lastName  = lastName;
        this.studentID = studentID;
        this.email     = email;
    }

    // ── Accessors ────────────────────────────────────────────────────────────

    /** @return the student's first name */
    public String getFirstName() { return firstName; }

    /** @return the student's last name */
    public String getLastName()  { return lastName; }

    /** @return the student's ID number */
    public int    getStudentID() { return studentID; }

    /** @return the student's email address */
    public String getEmail()     { return email; }

    // ── Mutators ─────────────────────────────────────────────────────────────

    /** @param firstName the new first name; must not be null */
    public void setFirstName(String firstName) {
        if (firstName == null) throw new IllegalArgumentException("First name must not be null.");
        this.firstName = firstName;
    }

    /** @param lastName the new last name; must not be null */
    public void setLastName(String lastName) {
        if (lastName == null) throw new IllegalArgumentException("Last name must not be null.");
        this.lastName = lastName;
    }

    /** @param studentID the new student ID */
    public void setStudentID(int studentID) { this.studentID = studentID; }

    /** @param email the new email address; must not be null */
    public void setEmail(String email) {
        if (email == null) throw new IllegalArgumentException("Email must not be null.");
        this.email = email;
    }

    // ── Utility ──────────────────────────────────────────────────────────────

    /**
     * Returns a single-line string representation suitable for writing to
     * {@code students.txt}, in the format:
     * {@code FirstName LastName StudentID Email}
     *
     * @return the file-format string for this student
     */
    public String toFileString() {
        return firstName + " " + lastName + " " + studentID + " " + email;
    }

    /**
     * Returns a human-readable description of this student.
     *
     * @return a formatted string showing all fields
     */
    @Override
    public String toString() {
        return "Student{firstName='" + firstName + "', lastName='" + lastName
                + "', studentID=" + studentID + ", email='" + email + "'}";
    }

    /**
     * Two students are equal if and only if their student IDs match.
     *
     * @param other the object to compare
     * @return {@code true} if both students have the same ID
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;
        Student s = (Student) other;
        return this.studentID == s.studentID;
    }

    @Override
    public int hashCode() { return Objects.hash(studentID); }
}