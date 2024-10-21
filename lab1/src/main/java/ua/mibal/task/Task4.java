package ua.mibal.task;

import ua.mibal.component.CityGatherer;
import ua.mibal.model.Participant;

import java.util.List;
import java.util.stream.Stream;

/**
 *
 * This class is a part of the Laboratory work solution.
 * Represents task 4
 *
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class Task4 {
    public static List<Participant> get(Stream<Participant> participants) {
        return participants
                .gather(new CityGatherer("Київ", 10, 500))
                .toList();
    }
}
