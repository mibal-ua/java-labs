package ua.mibal.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.mibal.codegen.api.GenField;
import ua.mibal.codegen.api.Serialize;
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
@XmlModel
@Serialize
public class Book implements Serializable {
    @GenField("title")
    @Field("title")
    private String title;
    @GenField("author")
    @Field("author")
    private String author;
    @GenField
    @Field
    private int year;
}
