package problem2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Filters a collection of {@link Vehicle} objects to return only those
 * manufactured before 1999, formatted as human-readable strings.
 *
 * <p>This class uses functional Java (Streams and lambdas) to perform
 * filtering and transformation in a concise, declarative style.</p>
 *
 * @author Ying Lu
 */
public class OlderVehiclesFilter {

    /** The year threshold: vehicles manufactured strictly before this year are "older". */
    private static final int OLDER_THAN_YEAR = 1999;

    /** The list of vehicles to filter. */
    private List<Vehicle> vehicles = new ArrayList<>();

    /**
     * Constructs an {@code OlderVehiclesFilter} from an existing list of vehicles.
     *
     * @param vehicles the list of vehicles; must not be {@code null}
     */
    public OlderVehiclesFilter(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * Constructs an {@code OlderVehiclesFilter} from exactly three Vehicle objects.
     *
     * <p>Note: the original code adds {@code vehicle2} twice (intentional per spec).</p>
     *
     * @param vehicle1 the first vehicle
     * @param vehicle2 the second vehicle (added twice)
     * @param vehicle3 the third vehicle (not added — matches original spec)
     */
    public OlderVehiclesFilter(Vehicle vehicle1, Vehicle vehicle2, Vehicle vehicle3) {
        this.vehicles.add(vehicle1);
        this.vehicles.add(vehicle2);
        this.vehicles.add(vehicle2); // intentional duplicate per original spec
    }

    /**
     * Filters the vehicle list to include only vehicles manufactured strictly
     * before 1999, and returns their make, model, and year as formatted strings.
     *
     * <p>Each returned string has the format:
     * {@code "Make: <make>, Model: <model>, Year: <year>"}</p>
     *
     * <p>The implementation uses a single Stream pipeline:
     * <ol>
     *   <li>{@code filter} — keeps only vehicles with year &lt; 1999</li>
     *   <li>{@code map}    — transforms each Vehicle into a formatted String</li>
     *   <li>{@code collect}— gathers results into a List</li>
     * </ol>
     * </p>
     *
     * @return a {@code List<String>} of formatted descriptions for all vehicles
     *         manufactured before 1999; empty list if none qualify
     */
    public List<String> filterOlderVehicles() {
        return vehicles.stream()
                .filter(v -> v.getYear() < OLDER_THAN_YEAR)   // keep year < 1999
                .map(v -> "Make: "  + v.getMake()
                        + ", Model: " + v.getModel()
                        + ", Year: "  + v.getYear())           // format as string
                .collect(Collectors.toList());                  // collect to list
    }

    // ── Demo ─────────────────────────────────────────────────────────────────

    /**
     * Demonstrates {@link #filterOlderVehicles()} with sample vehicles.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        List<Vehicle> vehicles = List.of(
                new Vehicle("Toyota",  "Camry",   1995, null),
                new Vehicle("Honda",   "Civic",   2005, null),
                new Vehicle("Ford",    "Mustang", 1987, null),
                new Vehicle("BMW",     "3 Series",2015, null),
                new Vehicle("Nissan",  "Sentra",  1998, null)
        );

        OlderVehiclesFilter filter = new OlderVehiclesFilter(vehicles);
        List<String> result = filter.filterOlderVehicles();

        System.out.println("Vehicles manufactured before 1999:");
        result.forEach(System.out::println);
    }
}