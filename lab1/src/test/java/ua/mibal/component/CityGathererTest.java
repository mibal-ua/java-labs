package ua.mibal.component;

import org.junit.jupiter.api.Test;
import ua.mibal.model.Participant;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
class CityGathererTest {
    private final CityGatherer gatherer = new CityGatherer("Kyiv", 2, 3);

    @Test
    void shouldReturnEmptyListIfSkipCityAndSkipCountIsBigger() {
        Stream<Participant> input = Stream.of(
                personAt("Kyiv"),
                personAt("Kyiv")
        );

        List<Participant> actual = input.gather(gatherer).toList();

        assertEquals(0, actual.size());
    }

    @Test
    void shouldReturnEmptyListIfSkipCityAndSkipCountIsLess() {
        Stream<Participant> input = Stream.of(
                personAt("Kyiv"),
                personAt("Kyiv"),
                personAt("Kyiv")
        );

        List<Participant> actual = input.gather(gatherer).toList();

        assertEquals(1, actual.size());
    }

    @Test
    void shouldReturnEmptyListIfSkipCity() {
        Stream<Participant> input = Stream.of(
                personAt("Dnipro"),
                personAt("Dnipro"),
                personAt("Dnipro")
        );

        List<Participant> actual = input.gather(gatherer).toList();

        assertEquals(3, actual.size());
    }

    @Test
    void shouldReturnEmptyListIfSkipCityAndLimit() {
        Stream<Participant> input = Stream.of(
                personAt("Dnipro"),
                personAt("Dnipro"),
                personAt("Dnipro"),
                personAt("Dnipro")
        );

        List<Participant> actual = input.gather(gatherer).toList();

        assertEquals(3, actual.size());
    }

    private Participant personAt(String city) {
        return new Participant("John", "Doe", LocalDate.now(), city, 1000);
    }
}
