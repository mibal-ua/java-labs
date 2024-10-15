package ua.mibal;

import ua.mibal.component.Gatherer;
import ua.mibal.component.Generator;
import ua.mibal.model.Participant;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class Launcher {

    public static void main(String[] args) {
        Stream<Participant> participants = Generator.generate();
        List<Participant> result = Gatherer.gather(participants, "Київ", 10, 500);
        result.forEach(System.out::println);
    }
}
