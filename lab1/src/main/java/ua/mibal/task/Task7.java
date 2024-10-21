package ua.mibal.task;

import ua.mibal.model.OutlierStatistics;
import ua.mibal.model.Participant;

import java.util.List;

/**
 * This class is a part of the Laboratory work solution.
 * Represents task 7
 *
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class Task7 {

    public static void complete(List<Participant> data) {
        List<Integer> outliers = findOutliersOf(data);
        OutlierStatistics statistics = new OutlierStatistics(
                data.size() - outliers.size(), outliers.size()
        );
        System.out.println(statistics);
    }

    private static List<Integer> findOutliersOf(List<Participant> data) {
        List<Integer> sorted = data.stream()
                .map(Participant::monthlyIncome)
                .sorted()
                .toList();
        int q3 = sorted.get(data.size() / 4 * 3);
        int q1 = sorted.get(data.size() / 4);
        
        int interquartileRange = q3 - q1;

        double upperBoundary = q3 + 1.5 * interquartileRange;
        double lowerBoundary = q1 - 1.5 * interquartileRange;

        return data.stream()
                .map(Participant::monthlyIncome)
                .filter(income -> income < lowerBoundary || upperBoundary < income)
                .toList();
    }
}
