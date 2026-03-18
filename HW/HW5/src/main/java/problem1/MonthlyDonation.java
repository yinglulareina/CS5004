package problem1;

import java.time.LocalDateTime;

public class MonthlyDonation extends Donation {
    private LocalDateTime cancellationTimestamp;

    public MonthlyDonation(double amount, LocalDateTime creationTimestamp) {
        super(amount, creationTimestamp);
        this.cancellationTimestamp = null; // No cancellation date when first created
    }

    /**
     * Sets the cancellation date, ensuring it's not before creation.
     */
    public void setCancellationTimestamp(LocalDateTime cancellationTimestamp) {
        if (cancellationTimestamp.isBefore(this.creationTimestamp)) {
            throw new IllegalArgumentException("Cancellation date cannot be before creation date.");
        }
        this.cancellationTimestamp = cancellationTimestamp;
    }

    @Override
    public double getAmountForYear(int year) {
        double total = 0.0;
        // Start from the creation date and check each month of the year
        LocalDateTime current = this.creationTimestamp;

        // If creation is after the target year, or if canceled before the target year
        if (current.getYear() > year || (cancellationTimestamp != null && cancellationTimestamp.getYear() < year)) {
            return 0.0;
        }

        // Iterate through months until we exceed the target year
        while (current.getYear() <= year) {
            if (current.getYear() == year) {
                // Only add if not canceled yet
                if (cancellationTimestamp == null || !current.isAfter(cancellationTimestamp)) {
                    total += this.amount;
                }
            }
            current = current.plusMonths(1);
        }
        return total;
    }
}