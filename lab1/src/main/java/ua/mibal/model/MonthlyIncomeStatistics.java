package ua.mibal.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents statistical information about the monthly income of participants.
 *
 * <p>This class provides methods to record income data and calculate various
 * statistical measures such as average, standard deviation, minimum, and maximum income.</p>
 *
 * <p>To add a single income record, use the {@link #record(int)} method.</p>
 * <p>To combine this statistics with another, use the {@link #record(MonthlyIncomeStatistics)} method.</p>
 *
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public final class MonthlyIncomeStatistics {
    private final List<Integer> data = new ArrayList<>();

    /**
     * Calculates and returns the average monthly income.
     *
     * @return The average monthly income as a double.
     */
    public double getAvg() {
        return (double) getSum() / data.size();
    }

    /**
     * Calculates and returns the standard deviation of monthly incomes.
     *
     * @return The standard deviation of monthly incomes as a double.
     */
    public double getDeviation() {
        double dispersion = calcDispersion();
        return Math.sqrt(dispersion);
    }

    /**
     * Records a single monthly income value.
     *
     * @param income The monthly income to be recorded.
     */
    public void record(int income) {
        data.add(income);
    }

    /**
     * Combines this statistics with another MonthlyIncomeStatistics object.
     *
     * @param stats The MonthlyIncomeStatistics object to be combined with this one.
     */
    public void record(MonthlyIncomeStatistics stats) {
        data.addAll(stats.data);
    }

    /**
     * Returns a string representation of the statistics, including minimum, maximum,
     * average, and standard deviation of monthly incomes.
     *
     * @return A string representation of the MonthlyIncomeStatistics.
     */
    @Override
    public String toString() {
        return "MonthlyIncomeStatistics[" +
               "min=" + getMin() + ", " +
               "max=" + getMax() + ", " +
               "avg=" + getAvg() + ", " +
               "deviation=" + getDeviation() + ']';
    }

    /**
     * Calculates the sum of all recorded monthly incomes.
     *
     * @return The sum of all monthly incomes as an Integer.
     */
    private Integer getSum() {
        return data.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    /**
     * Finds the maximum recorded monthly income.
     *
     * @return The maximum monthly income as an Integer, or 0 if no data is available.
     */
    private Integer getMax() {
        return data.stream()
                .max(Integer::compareTo)
                .orElse(0);
    }

    /**
     * Finds the minimum recorded monthly income.
     *
     * @return The minimum monthly income as an Integer, or 0 if no data is available.
     */
    private Integer getMin() {
        return data.stream()
                .min(Integer::compareTo)
                .orElse(0);
    }

    /**
     * Calculates the dispersion (variance) of the monthly incomes.
     *
     * @return The dispersion of monthly incomes as a double.
     */
    private double calcDispersion() {
        double avg = getAvg();
        double sum = 0;
        for (int income : data) {
            sum += Math.pow(income - avg, 2);
        }
        return sum / data.size();
    }
}
