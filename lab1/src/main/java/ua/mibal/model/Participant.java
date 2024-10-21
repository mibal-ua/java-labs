package ua.mibal.model;

import java.time.LocalDate;

/**
 * This class represents a person.
 * <p>
 * It is a record class, which means that it is immutable and has a default constructor.
 * <p>Example usage:</p>
 * <pre>
 * MonthlyIncomeStatistics stats = new MonthlyIncomeStatistics();
 * stats.record(3000);
 * stats.record(3500);
 * System.out.println("Average income: " + stats.getAvg());
 * System.out.println("Income deviation: " + stats.getDeviation());
 * </pre>
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public record Participant(
        String firstName,
        String lastName,
        LocalDate birthDate,
        String city,
        Integer monthlyIncome
) {
}
