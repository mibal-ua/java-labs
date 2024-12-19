package ua.mibal.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.mibal.serializer.reflection.annotation.Field;
import ua.mibal.serializer.reflection.annotation.XmlModel;

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
@XmlModel("operation")
public class Transaction implements Serializable {
    @Field("ID")
    private String id;
    private String secret;
    @Field
    private String sender;
    @Field
    private String recipient;
    @Field
    private double amount;
    @Field
    private String currency;
}
