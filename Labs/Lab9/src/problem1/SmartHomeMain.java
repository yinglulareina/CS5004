package problem1;

import java.util.ArrayList;
import java.util.List;

// ─────────────────────────────────────────────────────────────────────────────
// SmartDevice interface
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Common interface for all smart home devices.
 * Every device can be turned on/off and can report its status.
 */
interface SmartDevice {
    void turnOn();
    void turnOff();
    String getStatus();
}

// ─────────────────────────────────────────────────────────────────────────────
// Concrete devices
// ─────────────────────────────────────────────────────────────────────────────

/**
 * A smart light bulb that implements SmartDevice.
 */
class SmartLight implements SmartDevice {
    private final String name;
    private boolean on = false;

    public SmartLight(String name) { this.name = name; }

    @Override public void turnOn()  { on = true;  System.out.println(name + " light turned ON."); }
    @Override public void turnOff() { on = false; System.out.println(name + " light turned OFF."); }
    @Override public String getStatus() { return name + " light is " + (on ? "ON" : "OFF"); }
}

/**
 * A smart speaker that implements SmartDevice.
 */
class SmartSpeaker implements SmartDevice {
    private final String name;
    private boolean on = false;

    public SmartSpeaker(String name) { this.name = name; }

    @Override public void turnOn()  { on = true;  System.out.println(name + " speaker turned ON."); }
    @Override public void turnOff() { on = false; System.out.println(name + " speaker turned OFF."); }
    @Override public String getStatus() { return name + " speaker is " + (on ? "ON" : "OFF"); }
}

// ─────────────────────────────────────────────────────────────────────────────
// PATTERN 1: Singleton — SmartHomeController
// ─────────────────────────────────────────────────────────────────────────────

/**
 * SINGLETON pattern.
 *
 * Only one SmartHomeController can exist. It manages a list of all
 * registered smart devices in the home.
 *
 * The constructor is private so no one can call "new SmartHomeController()".
 * The only way to get the instance is through getInstance().
 */
class SmartHomeController {

    // the single instance, created lazily on first access
    private static SmartHomeController instance = null;

    private final List<SmartDevice> devices = new ArrayList<>();

    // private constructor prevents external instantiation
    private SmartHomeController() {
        System.out.println("SmartHomeController created (Singleton).");
    }

    /**
     * Returns the single shared instance, creating it if needed.
     *
     * @return the singleton SmartHomeController
     */
    public static SmartHomeController getInstance() {
        if (instance == null) {
            instance = new SmartHomeController();
        }
        return instance;
    }

    /** Registers a device with this controller. */
    public void addDevice(SmartDevice device) {
        devices.add(device);
        System.out.println("Device added: " + device.getStatus());
    }

    /** Prints the status of every registered device. */
    public void printAllStatus() {
        System.out.println("\n--- All Device Status ---");
        for (SmartDevice d : devices) {
            System.out.println("  " + d.getStatus());
        }
        System.out.println("-------------------------");
    }

    /** Returns the list of managed devices (used by the Facade). */
    public List<SmartDevice> getDevices() { return devices; }
}

// ─────────────────────────────────────────────────────────────────────────────
// PATTERN 2: Factory Method — DeviceFactory
// ─────────────────────────────────────────────────────────────────────────────

/**
 * FACTORY METHOD pattern.
 *
 * Centralises object creation. Callers ask for a device by type string
 * instead of calling "new SmartLight(...)" directly. This means if we
 * ever add a SmartTV, only this class needs to change.
 */
class DeviceFactory {

    /**
     * Creates and returns a SmartDevice of the requested type.
     *
     * @param type "light" or "speaker"
     * @param name a label for the device
     * @return a new SmartDevice instance
     * @throws IllegalArgumentException if the type is unknown
     */
    public static SmartDevice createDevice(String type, String name) {
        switch (type.toLowerCase()) {
            case "light":   return new SmartLight(name);
            case "speaker": return new SmartSpeaker(name);
            default: throw new IllegalArgumentException("Unknown device type: " + type);
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// PATTERN 3: Adapter — LegacyThermostatAdapter
// ─────────────────────────────────────────────────────────────────────────────

/**
 * The old thermostat we cannot modify — it has an incompatible interface.
 */
class LegacyThermostat {
    public void activate()       { System.out.println("Legacy thermostat: heating activated."); }
    public void deactivate()     { System.out.println("Legacy thermostat: heating deactivated."); }
    public int  getCurrentTemp() { return 22; }
}

/**
 * ADAPTER pattern.
 *
 * Wraps LegacyThermostat so it looks like a SmartDevice to the rest of
 * the system. The controller and facade never need to know about the old
 * interface — they just call turnOn() / turnOff() / getStatus().
 *
 * Think of it like a power-plug adapter: same electricity, different socket.
 */
class LegacyThermostatAdapter implements SmartDevice {

    private final LegacyThermostat thermostat; // the adaptee we're wrapping
    private boolean on = false;

    public LegacyThermostatAdapter(LegacyThermostat thermostat) {
        this.thermostat = thermostat;
    }

    @Override
    public void turnOn() {
        thermostat.activate();   // translate turnOn() → activate()
        on = true;
    }

    @Override
    public void turnOff() {
        thermostat.deactivate(); // translate turnOff() → deactivate()
        on = false;
    }

    @Override
    public String getStatus() {
        return "Thermostat is " + (on ? "ON" : "OFF")
                + ", current temp: " + thermostat.getCurrentTemp() + "°C";
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// PATTERN 4: Facade — SmartHomeFacade
// ─────────────────────────────────────────────────────────────────────────────

/**
 * FACADE pattern.
 *
 * Hides the complexity of controlling many devices behind simple,
 * high-level methods. The user calls activateNightMode() instead of
 * manually iterating over every device.
 *
 * The facade talks to the Singleton controller to get all devices,
 * then orchestrates them.
 */
class SmartHomeFacade {

    private final SmartHomeController controller;

    public SmartHomeFacade() {
        // gets the single shared controller
        this.controller = SmartHomeController.getInstance();
    }

    /**
     * Night mode: turn off lights, leave speaker on for white noise.
     */
    public void activateNightMode() {
        System.out.println("\n>> Night Mode activated:");
        for (SmartDevice d : controller.getDevices()) {
            if (d instanceof SmartLight)   d.turnOff();
            if (d instanceof SmartSpeaker) d.turnOn();
        }
    }

    /**
     * Leave home: turn everything off.
     */
    public void leaveHome() {
        System.out.println("\n>> Leaving home — turning everything off:");
        for (SmartDevice d : controller.getDevices()) {
            d.turnOff();
        }
    }

    /**
     * Morning mode: turn on lights and speaker.
     */
    public void morningMode() {
        System.out.println("\n>> Morning Mode activated:");
        for (SmartDevice d : controller.getDevices()) {
            d.turnOn();
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Main — demonstration
// ─────────────────────────────────────────────────────────────────────────────

public class SmartHomeMain {
    public static void main(String[] args) {

        // ── Singleton ────────────────────────────────────────────────────────
        SmartHomeController controller = SmartHomeController.getInstance();
        SmartHomeController same       = SmartHomeController.getInstance();
        System.out.println("Same instance? " + (controller == same)); // true

        // ── Factory Method ───────────────────────────────────────────────────
        SmartDevice livingLight   = DeviceFactory.createDevice("light",   "Living Room");
        SmartDevice bedroomLight  = DeviceFactory.createDevice("light",   "Bedroom");
        SmartDevice kitchenSpeaker = DeviceFactory.createDevice("speaker","Kitchen");

        // ── Adapter ──────────────────────────────────────────────────────────
        LegacyThermostat       oldThermostat = new LegacyThermostat();
        SmartDevice            thermoAdapter = new LegacyThermostatAdapter(oldThermostat);

        // Add all devices (including the adapted legacy one) to the controller
        controller.addDevice(livingLight);
        controller.addDevice(bedroomLight);
        controller.addDevice(kitchenSpeaker);
        controller.addDevice(thermoAdapter);

        controller.printAllStatus();

        // ── Facade ───────────────────────────────────────────────────────────
        SmartHomeFacade facade = new SmartHomeFacade();
        facade.morningMode();
        controller.printAllStatus();

        facade.activateNightMode();
        controller.printAllStatus();

        facade.leaveHome();
        controller.printAllStatus();
    }
}