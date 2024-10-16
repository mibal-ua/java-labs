package ua.mibal.model;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public record MonthlyIncomeStatistics(
        int min,
        int max,
        int avg,
        int standardSmthWtf //rename стандартне відхилення
) {
}
