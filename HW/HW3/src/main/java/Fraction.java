/**
 * Fraction interface represents a rational number.
 * It extends Comparable to provide ordering based on the fraction's value.
 */
public interface Fraction extends Comparable<Fraction> {
    /**
     * Gets the numerator of this fraction.
     * @return the numerator of this fraction */
    int getNumerator();

    /**
     * Gets the denominator of this fraction.
     * @return the denominator of this fraction */
    int getDenominator();

    /**
     * Sets the numerator of this fraction.
     * @param n the new numerator */
    void setNumerator(int n);

    /**
     * Sets the denominator of this fraction.
     * @param d the new numerator */
    void setDenominator(int d);

    /**
     * Returns the decimal value of the fraction (numerator / denominator).
     * @return the decimal value of the fraction */
    double toDouble();

    /**
     * Returns a new fraction that is the reciprocal of the current one.
     * @return a new fraction that is the reciprocal of the current one */
    Fraction reciprocal();

    /**
     * Adds the specified fraction to this fraction.
     * @param other the fraction to add
     * @return a new fraction that is the sum */
    Fraction add(Fraction other);

    /**
     * Compares this fraction with the specified fraction for order.
     * @param other the fraction to compare to
     * @return a negative integer, zero, or a positive integer */
    @Override
    int compareTo(Fraction other);
}