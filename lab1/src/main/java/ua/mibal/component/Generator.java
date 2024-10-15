package ua.mibal.component;

import ua.mibal.model.Participant;

import java.time.LocalDate;
import java.util.stream.Stream;

/**
 * This class generates random participants.
 * <p>
 * It is used to generate random participants for the application.
 * It is a component class, which means that it is used to perform a specific task.
 *
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class Generator {
    private static final String[] firstNames = { "Олександр", "Микола", "Ольга", "Ірина", "Дмитро" };
    private static final String[] lastNames = { "Ковальчук", "Петренко", "Іванов", "Сидоренко", "Бондар" };
    private static final String[] cities = { "Київ", "Львів", "Одеса", "Харків", "Дніпро" };

    public static Stream<Participant> generate() {
        return Stream.generate(this::createPerson);
    }

    private static Participant createPerson() {
        return new Participant(
                randomOf(firstNames),
                randomOf(lastNames),
                randomBirthDate(),
                randomOf(cities),
                randomMonthlyIncome()
        );
    }

    private static LocalDate randomBirthDate() {
        int year = 1970 + randomTo(35);
        int month = 1 + randomTo(12);
        int day = 1 + randomTo(28);
        return LocalDate.of(year, month, day);
    }

    private static Integer randomMonthlyIncome() {
        return 20_000 + randomTo(100_000);
    }

    private static String randomOf(String[] array) {
        return array[randomTo(array.length)];
    }

    private static int randomTo(int limit) {
        return (int) (Math.random() * limit);
    }
}
