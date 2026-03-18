package problem1;

import java.time.LocalDateTime;

public class OneTimeDonation extends Donation {

    public OneTimeDonation(double amount, LocalDateTime creationTimestamp) {
        super(amount, creationTimestamp);
    }

    @Override
    public double getAmountForYear(int year) {
        // Included only in the year the donation was created
        if (this.creationTimestamp.getYear() == year) {
            return this.amount;
        }
        return 0.0;
    }
}