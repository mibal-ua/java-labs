package ua.mibal.model;

import java.util.Date;

/**
 * This class represents a person.
 * <p> 
 * It is a record class, which means that it is immutable and has a default constructor.
 * 
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public record Person(
        String firstName,
        String lastName,
        Date birthDate,
        String city,
        Integer salary
) {
}
