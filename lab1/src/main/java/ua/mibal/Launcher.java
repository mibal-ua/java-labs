package ua.mibal;

import ua.mibal.component.MonthlyIncomeStatisticGeneratorCollector;
import ua.mibal.component.Gatherer;
import ua.mibal.component.Generator;
import ua.mibal.model.MonthlyIncomeStatistics;
import ua.mibal.model.Participant;

import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.time.LocalDate.now;
import static java.util.stream.Collectors.groupingBy;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class Launcher {

    public static void main(String[] args) {
        Stream<Participant> participants = Generator.generate();
        List<Participant> gathered = Gatherer.gather(participants, "Київ", 10, 500);

        List<Participant> filteredByAge = filterByAge(gathered, 20, 35);
        Map<String, List<Participant>> groupedByName = groupByName(filteredByAge);

        MonthlyIncomeStatistics monthlyIncome = gathered.stream()
                .collect(new MonthlyIncomeStatisticGeneratorCollector());
        System.out.println(monthlyIncome);

        // todo 7

        groupedByName.forEach((name, list) -> {
            System.out.println(name + ":");
            list.forEach(System.out::println);
            System.out.println();
        });
    }

    private static Map<String, List<Participant>> groupByName(List<Participant> participants) {
        return participants.stream()
                .collect(groupingBy(Participant::firstName));
    }

    private static List<Participant> filterByAge(List<Participant> participants, int min, int max) {
        return participants.stream()
                .filter(p -> {
                    Period period = Period.between(p.birthDate(), now());
                    int age = period.getYears();
                    return min <= age && age <= max;
                })
                .toList();
    }
}
