package ua.mibal.component;

import ua.mibal.model.Participant;

import java.time.LocalDate;
import java.util.stream.Stream;

/**
 * A utility class for generating random {@link Participant} objects.
 * <p>
 * This class provides functionality to create a stream of random participants
 * with realistic data for testing or demonstration purposes. It generates
 * random names, birth dates, cities, and monthly incomes for each participant.
 *
 * <p>Example usage:</p>
 * <pre>
 * Stream<Participant> participants = Generator.generate();
 * participants.limit(10).forEach(System.out::println);
 * </pre>
 *
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class Generator {
    private static final String[] firstNames = { "Олександр", "Микола", "Ольга", "Ірина", "Дмитро" };
    private static final String[] lastNames = { "Ковальчук", "Петренко", "Іванов", "Сидоренко", "Бондар" };
    private static final String[] cities = { "Київ", "Львів", "Одеса", "Харків", "Дніпро" };

    public static Stream<Participant> generate() {
        return Stream.generate(Generator::createPerson);
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
