package ua.mibal.task;

import ua.mibal.component.Generator;
import ua.mibal.model.Participant;

import java.util.stream.Stream;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class Task3 {

    public static Stream<Participant> get() {
        return Generator.generate();
    }
}
