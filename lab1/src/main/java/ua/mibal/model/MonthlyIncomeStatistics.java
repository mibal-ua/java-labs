package ua.mibal.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public final class MonthlyIncomeStatistics {
    private final List<Integer> data = new ArrayList<>();

    public double getAvg() {
        return (double) getSum() / data.size();
    }

    public double getDeviation() {
        double dispersion = calcDispersion();
        return Math.sqrt(dispersion);
    }

    public void record(int income) {
        data.add(income);
    }

    public void record(MonthlyIncomeStatistics stats) {
        data.addAll(stats.data);
    }

    @Override
    public String toString() {
        return "MonthlyIncomeStatistics[" +
               "min=" + getMin() + ", " +
               "max=" + getMax() + ", " +
               "avg=" + getAvg() + ", " +
               "deviation=" + getDeviation() + ']';
    }

    private Integer getSum() {
        return data.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private Integer getMax() {
        return data.stream()
                .max(Integer::compareTo)
                .orElse(0);
    }

    private Integer getMin() {
        return data.stream()
                .min(Integer::compareTo)
                .orElse(0);
    }

    private double calcDispersion() {
        double avg = getAvg();
        double sum = 0;
        for (int income : data) {
            sum += Math.pow(income - avg, 2);
        }
        return sum / data.size();
    }
}
