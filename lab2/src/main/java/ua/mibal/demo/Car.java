package ua.mibal.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.mibal.serializer.annotation.XmlModel;

import java.io.Serializable;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@ToString
@Builder
@AllArgsConstructor
@Getter
@Setter
@XmlModel
public class Car implements Serializable {
    private String brand;
    private String model;
    private int year;
}
