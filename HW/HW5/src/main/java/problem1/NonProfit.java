package problem1;

import java.util.ArrayList;
import java.util.Collection;

public class NonProfit {
    private String name;
    private Collection<Donation> donations; // Tracks a collection of all donations [cite: 26, 107]

    public NonProfit(String name) {
        this.name = name;
        this.donations = new ArrayList<>();
    }

    public void addDonation(Donation donation) {
        this.donations.add(donation);
    }

    /**
     * Calculates the sum of all donations processed in the specified year[cite: 27, 108].
     * Uses subtype polymorphism to call getAmountForYear on different donation types.
     */
    public double getTotalDonationsForYear(int year) {
        double total = 0.0;
        for (Donation d : donations) {
            // Polymorphism: Java knows which version of getAmountForYear to call at runtime
            total += d.getAmountForYear(year);
        }
        return total;
    }
}