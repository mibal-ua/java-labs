package ua.mibal;

import ua.mibal.model.Participant;
import ua.mibal.task.Task3;
import ua.mibal.task.Task4;
import ua.mibal.task.Task5;
import ua.mibal.task.Task6;
import ua.mibal.task.Task7;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class Launcher {

    public static void main(String[] args) {
        Stream<Participant> participants = Task3.get();

        List<Participant> gathered = Task4.get(participants);

        Task5.complete(gathered);

        Task6.complete(gathered);
        
        Task7.complete(gathered);
    }
}
