package ua.mibal.component;

import ua.mibal.model.Person;

import java.util.Date;
import java.util.stream.Stream;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class PersonGenerator {
    
    public Stream<Person> generate(int count) {
        return Stream
                .generate(this::createPerson)
                .limit(count);
    }

    private Person createPerson() {
        return new Person(
                randomFirstName(),
                randomLastName(),
                randomBirthDate(),
                randomCity(),
                randomSalary()
        );
    }

    // todo: implement random values generation
    private String randomFirstName() {
        return "John";
    }

    private String randomLastName() {
        return "Doe";
    }

    private Date randomBirthDate() {
        return new Date();
    }

    private String randomCity() {
        return "New York";
    }

    private Integer randomSalary() {
        return 1000;
    }
}
