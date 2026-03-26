import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main program for reading, displaying, and managing student records stored
 * in {@code students.txt}.
 *
 * <p>On startup the program loads all students from the file into an
 * {@link ArrayList}. The user is then presented with a menu offering three
 * operations:</p>
 * <ol>
 *   <li><b>Add</b> a new student (updates the file and prints the new list)</li>
 *   <li><b>Remove</b> a student by ID (updates the file)</li>
 *   <li><b>Search</b> for a student by ID</li>
 * </ol>
 *
 * @author Ying Lu
 */
public class StudentReader {

    /** Path to the student data file. */
    private static final String FILE_PATH = "students.txt";

    /**
     * Entry point. Loads students from file, prints the initial list,
     * then runs the interactive menu loop.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ArrayList<Student> students = loadStudents(FILE_PATH);

        System.out.println("=== Student Database Loaded ===");
        printStudents(students);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    addStudent(students, scanner);
                    break;
                case "2":
                    removeStudent(students, scanner);
                    break;
                case "3":
                    searchStudent(students, scanner);
                    break;
                case "4":
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
        }
        scanner.close();
    }

    // ── File I/O ─────────────────────────────────────────────────────────────

    /**
     * Reads all student records from the given file path and returns them
     * as an {@link ArrayList}.
     *
     * <p>Each non-blank line must follow the format:
     * {@code FirstName LastName StudentID Email}</p>
     *
     * @param filePath the path to the student data file
     * @return an ArrayList of {@link Student} objects; empty if file is missing
     */
    public static ArrayList<Student> loadStudents(String filePath) {
        ArrayList<Student> students = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Warning: " + filePath + " not found. Starting with empty list.");
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+"); // split on whitespace
                if (parts.length < 4) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }
                String firstName = parts[0];
                String lastName  = parts[1];
                int    studentID = Integer.parseInt(parts[2]);
                String email     = parts[3];
                students.add(new Student(firstName, lastName, studentID, email));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return students;
    }

    /**
     * Writes all students in the list back to the file, overwriting its
     * previous content.
     *
     * @param students the current list of students
     * @param filePath the path to the file to write
     */
    public static void saveStudents(ArrayList<Student> students, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Student s : students) {
                writer.write(s.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // ── Menu Operations ──────────────────────────────────────────────────────

    /**
     * Prompts the user for new student details, adds the student to the list,
     * updates the file, and prints the updated list.
     *
     * @param students the current list of students
     * @param scanner  the scanner reading user input
     */
    public static void addStudent(ArrayList<Student> students, Scanner scanner) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Enter student ID: ");
        int studentID;
        try {
            studentID = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Student not added.");
            return;
        }

        // Check for duplicate ID
        if (findByID(students, studentID) != null) {
            System.out.println("Error: A student with ID " + studentID + " already exists.");
            return;
        }

        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

        Student newStudent = new Student(firstName, lastName, studentID, email);
        students.add(newStudent);
        saveStudents(students, FILE_PATH);

        System.out.println("\nStudent added successfully!");
        printStudents(students);
    }

    /**
     * Prompts the user for a student ID, removes the matching student from
     * the list, and updates the file.
     *
     * @param students the current list of students
     * @param scanner  the scanner reading user input
     */
    public static void removeStudent(ArrayList<Student> students, Scanner scanner) {
        System.out.print("Enter student ID to remove: ");
        int targetID;
        try {
            targetID = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
            return;
        }

        Student toRemove = findByID(students, targetID);
        if (toRemove == null) {
            System.out.println("Error: No student with ID " + targetID + " found.");
            return;
        }

        students.remove(toRemove);
        saveStudents(students, FILE_PATH);
        System.out.println("Student with ID " + targetID + " removed successfully.");
    }

    /**
     * Prompts the user for a student ID and displays the matching student's
     * details, or an error message if not found.
     *
     * @param students the current list of students
     * @param scanner  the scanner reading user input
     */
    public static void searchStudent(ArrayList<Student> students, Scanner scanner) {
        System.out.print("Enter student ID to search: ");
        int targetID;
        try {
            targetID = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
            return;
        }

        Student found = findByID(students, targetID);
        if (found == null) {
            System.out.println("Error: No student with ID " + targetID + " exists in the database.");
        } else {
            System.out.println("Student found:");
            System.out.println("  Name:  " + found.getFirstName() + " " + found.getLastName());
            System.out.println("  ID:    " + found.getStudentID());
            System.out.println("  Email: " + found.getEmail());
        }
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    /**
     * Searches the list for a student with the given ID.
     *
     * @param students the list to search
     * @param id       the target student ID
     * @return the matching {@link Student}, or {@code null} if not found
     */
    public static Student findByID(ArrayList<Student> students, int id) {
        for (Student s : students) {
            if (s.getStudentID() == id) return s;
        }
        return null;
    }

    /**
     * Prints all students with their line numbers (1-based).
     *
     * @param students the list of students to display
     */
    public static void printStudents(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("(No students in the database.)");
            return;
        }
        System.out.println();
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.printf("  %d. %-12s %-12s  ID: %-8d  Email: %s%n",
                    i + 1,
                    s.getFirstName(),
                    s.getLastName(),
                    s.getStudentID(),
                    s.getEmail());
        }
        System.out.println();
    }

    /**
     * Prints the main menu options to the console.
     */
    private static void printMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Add a student");
        System.out.println("2. Remove a student by ID");
        System.out.println("3. Search for a student by ID");
        System.out.println("4. Exit");
    }
}