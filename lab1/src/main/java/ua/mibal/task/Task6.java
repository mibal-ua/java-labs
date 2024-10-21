package ua.mibal.task;

import ua.mibal.component.MonthlyIncomeStatisticGeneratorCollector;
import ua.mibal.model.MonthlyIncomeStatistics;
import ua.mibal.model.Participant;

import java.util.List;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class Task6 {
    public static void complete(List<Participant> gathered) {
        System.out.println(
                "calc statistics for: " + gathered.stream()
                        .map(Participant::monthlyIncome)
                        .toList()
        );
        MonthlyIncomeStatistics monthlyIncome = gathered.stream()
                .collect(new MonthlyIncomeStatisticGeneratorCollector());
        System.out.println(monthlyIncome);
    }
}
