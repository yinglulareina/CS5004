/**
 * FractionImpl implements the Fraction interface, representing a rational number.
 * It ensures the fraction is always stored in its simplest form with a positive denominator.
 */
public class FractionImpl implements Fraction {
    private int numerator;
    private int denominator;

    /**
     * Constructs a new FractionImpl and normalizes it.
     * @param numerator the numerator of the fraction
     * @param denominator the denominator of the fraction (must not be zero)
     * @throws IllegalArgumentException if denominator is 0
     */
    public FractionImpl(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        this.normalize();
    }

    /**
     * Computes the greatest common divisor using Euclid's algorithm.
     * @param a first integer
     * @param b second integer
     * @return the greatest common divisor
     */
    private static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    /**
     * Maintains invariants: denominator is always positive and
     * the fraction is in its simplest form.
     */
    private void normalize() {
        if (this.denominator < 0) {
            this.numerator = -this.numerator;
            this.denominator = -this.denominator;
        }

        int common = gcd(this.numerator, this.denominator);
        this.numerator = numerator / common;
        this.denominator = denominator / common;
    }

    /** @return the numerator of this fraction */
    @Override
    public int getNumerator() {
        return this.numerator;
    }

    /** @return the denominator of this fraction */
    @Override
    public int getDenominator() {
        return this.denominator;
    }

    /**
     * Sets the numerator and re-normalizes the fraction.
     * @param n the new numerator
     */
    @Override
    public void setNumerator(int n) {
        this.numerator = n;
        normalize();
    }

    /**
     * Sets the denominator and re-normalizes the fraction.
     * @param d the new denominator (must be positive)
     * @throws IllegalArgumentException if d is less than or equals to zero.
     */
    @Override
    public void setDenominator(int d) {
        if (d <= 0) {
            throw new IllegalArgumentException("Denominator cannot be zero or negative.");
        }
        this.denominator = d;
        normalize();
    }

    /**
     * Returns a string representation of this fraction in its simplest form.
     * Example: "1 / 2", "0 / 1".
     * @return the formatted fraction string.
     */
    @Override
    public String toString() {
        return (numerator) + " / " + (denominator);
    }

    /**
     * Returns the decimal value of the fraction.
     * @return the decimal value.
     */
    @Override
    public double toDouble() {
        return (double) this.numerator / this.denominator;
    }

    /**
     * Returns a new fraction that is the reciprocal of the current one.
     * @return a new reciprocal fraction.
     * @throws IllegalArgumentException if the numerator is zero.
     */
    @Override
    public Fraction reciprocal() {
        if (this.numerator == 0) {
            throw new IllegalArgumentException("Cannot take reciprocal of zero.");
        }
        return new FractionImpl(this.denominator, this.numerator);
    }

    /**
     * Adds this fraction to another fraction.
     * Uses long for intermediate calculations to prevent overflow.
     * @param other the fraction to add
     * @return a new Fraction representing the sum in simplest form.
     */
    @Override
    public Fraction add(Fraction other) {
        long n1 = this.getNumerator();
        long d1 = this.getDenominator();
        long n2 = other.getNumerator();
        long d2 = other.getDenominator();

        long newNum = n1 * d2 + n2 * d1;
        long newDom = d1 * d2;
        return new FractionImpl((int) newNum, (int) newDom);
    }

    /**
     * Compares this fraction with another.
     * Uses cross-multiplication with long to safely handle large values.
     * @param other the fraction to compare to
     * @return negative/zero/positive integer as this is less/equal/greater than other.
     */
    @Override
    public int compareTo(Fraction other) {
        long first = (long) this.numerator * other.getDenominator();
        long second = (long) other.getNumerator() * this.denominator;
        return Long.compare(first, second);
    }
}