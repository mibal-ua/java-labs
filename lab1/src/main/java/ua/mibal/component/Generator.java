package ua.mibal.component;

import ua.mibal.model.Participant;

import java.time.LocalDate;
import java.util.stream.Stream;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class Generator {
    private static final String[] firstNames = { "Олександр", "Микола", "Ольга", "Ірина", "Дмитро" };
    private static final String[] lastNames = { "Ковальчук", "Петренко", "Іванов", "Сидоренко", "Бондар" };
    private static final String[] cities = { "Київ", "Львів", "Одеса", "Харків", "Дніпро" };

    public Stream<Participant> generate() {
        return Stream.generate(this::createPerson);
    }

    private Participant createPerson() {
        return new Participant(
                randomOf(firstNames),
                randomOf(lastNames),
                randomBirthDate(),
                randomOf(cities),
                randomMonthlyIncome()
        );
    }

    private LocalDate randomBirthDate() {
        int year = 1970 + randomTo(35);
        int month = 1 + randomTo(12);
        int day = 1 + randomTo(28);
        return LocalDate.of(year, month, day);
    }

    private Integer randomMonthlyIncome() {
        return 20_000 + randomTo(100_000);
    }

    private String randomOf(String[] array) {
        return array[randomTo(array.length)];
    }

    private int randomTo(int limit) {
        return (int) (Math.random() * limit);
    }
}
