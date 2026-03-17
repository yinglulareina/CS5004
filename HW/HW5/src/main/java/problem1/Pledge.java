package problem1;

import java.time.LocalDateTime;

public class Pledge extends Donation {
    private LocalDateTime processingTimestamp;

    public Pledge(double amount, LocalDateTime creationTimestamp) {
        super(amount, creationTimestamp);
        this.processingTimestamp = null; // Processing date may or may not be provided [cite: 21, 102]
    }

    /**
     * Updates processing date, ensuring it's not before creation[cite: 23, 104].
     */
    public void setProcessingTimestamp(LocalDateTime processingTimestamp) {
        if (processingTimestamp != null && processingTimestamp.isBefore(this.creationTimestamp)) {
            throw new IllegalArgumentException("Processing date cannot be before creation date.");
        }
        this.processingTimestamp = processingTimestamp;
    }

    @Override
    public double getAmountForYear(int year) {
        // Only included in the year the donation is processed [cite: 32, 114]
        if (processingTimestamp != null && processingTimestamp.getYear() == year) {
            return this.amount;
        }
        // Not included if no processing date is set [cite: 33, 115]
        return 0.0;
    }
}