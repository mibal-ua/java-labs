package ua.mibal.task;

import ua.mibal.model.Participant;

import java.time.Period;
import java.util.List;
import java.util.Map;

import static java.time.LocalDate.now;
import static java.util.stream.Collectors.groupingBy;

/**
 *
 * This class is a part of the Laboratory work solution.
 * Represents task 5
 *
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class Task5 {

    public static void complete(List<Participant> gathered) {
        List<Participant> filteredByAge = filterByAge(gathered, 20, 35);
        Map<String, List<Participant>> groupedByName = groupByName(filteredByAge);
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
