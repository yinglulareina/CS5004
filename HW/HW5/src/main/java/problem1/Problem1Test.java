package problem1;

import java.time.LocalDateTime;

/**
 * Test class to verify donation calculation logic.
 * This class is for local testing.
 */
public class Problem1Test {
    public static void main(String[] args) {
        // 1. Create a NonProfit organization
        NonProfit myOrg = new NonProfit("Tech for All");

        // 2. Test One-Time Donation
        // Created in 2026, should only be counted in 2026
        Donation oneTime = new OneTimeDonation(100.0, LocalDateTime.of(2026, 1, 1, 10, 0));
        myOrg.addDonation(oneTime);

        // 3. Test Monthly Donation (The 11 * $10 example from assignment)
        // Created on 2/10/2026. For 2026, it should count Feb to Dec (11 months)
        MonthlyDonation monthly = new MonthlyDonation(10.0, LocalDateTime.of(2026, 2, 10, 10, 0));
        myOrg.addDonation(monthly);

        // 4. Test Pledge
        // Created in 2025, but processed in 2026. Should count in 2026.
        Pledge pledge = new Pledge(500.0, LocalDateTime.of(2025, 12, 20, 10, 0));
        pledge.setProcessingTimestamp(LocalDateTime.of(2026, 6, 1, 10, 0));
        myOrg.addDonation(pledge);

        // 5. Test Pledge with NO processing date
        // Should NOT be counted in 2026
        Pledge unpaidPledge = new Pledge(1000.0, LocalDateTime.of(2026, 1, 1, 10, 0));
        myOrg.addDonation(unpaidPledge);

        // --- Verification ---
        System.out.println("Organization: Tech for All");

        // Expected for 2026:
        // One-time (100) + Monthly (11 * 10 = 110) + Pledge (500) = 710.0
        double total2026 = myOrg.getTotalDonationsForYear(2026);
        System.out.println("Total for 2026: $" + total2026);
        System.out.println("Expected: $710.0");

        // Expected for 2025: 0.0 (The pledge was created in 2025 but processed in 2026)
        double total2025 = myOrg.getTotalDonationsForYear(2025);
        System.out.println("Total for 2025: $" + total2025);
        System.out.println("Expected: $0.0");

        // 6. Test Monthly Cancellation
        // If we cancel the monthly donation in May 2026
        monthly.setCancellationTimestamp(LocalDateTime.of(2026, 5, 15, 10, 0));
        // New calculation: Feb, March, April, May (4 months * $10 = $40)
        // Total: 100 (One-time) + 40 (Monthly) + 500 (Pledge) = 640.0
        System.out.println("Total after monthly cancellation: $" + myOrg.getTotalDonationsForYear(2026));
        System.out.println("Expected after cancellation: $640.0");
    }
}