package problem1;

import java.time.LocalDateTime;

/**
 * Abstract base class for all types of donations.
 * Demonstrates inheritance and abstract method definition.
 */
public abstract class Donation {
    protected double amount;
    protected LocalDateTime creationTimestamp;

    public Donation(double amount, LocalDateTime creationTimestamp) {
        this.amount = amount;
        this.creationTimestamp = creationTimestamp;
    }

    /**
     * Abstract method to calculate total amount for a given year.
     * Each subclass will provide its own specific implementation (Overriding).
     */
    public abstract double getAmountForYear(int year);
}