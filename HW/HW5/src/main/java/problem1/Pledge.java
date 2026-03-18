package problem1;

import java.time.LocalDateTime;

public class Pledge extends Donation {
    private LocalDateTime processingTimestamp;

    public Pledge(double amount, LocalDateTime creationTimestamp) {
        super(amount, creationTimestamp);
        this.processingTimestamp = null; // Processing date may or may not be provided
    }

    /**
     * Updates processing date, ensuring it's not before creation.
     */
    public void setProcessingTimestamp(LocalDateTime processingTimestamp) {
        if (processingTimestamp != null && processingTimestamp.isBefore(this.creationTimestamp)) {
            throw new IllegalArgumentException("Processing date cannot be before creation date.");
        }
        this.processingTimestamp = processingTimestamp;
    }

    @Override
    public double getAmountForYear(int year) {
        // Only included in the year the donation is processed
        if (processingTimestamp != null && processingTimestamp.getYear() == year) {
            return this.amount;
        }
        // Not included if no processing date is set
        return 0.0;
    }
}