package ua.mibal.codegen.api;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation to mark fields that should be included in the generated serializer.
 * The {@code GenField} annotation allows specifying a custom name for the field during serialization.
 * If no custom name is provided, the default field name is used.
 * <p>
 * This annotation is typically used in combination with the {@link Serialize} annotation to define
 * which fields should be serialized in formats like JSON or XML.
 *
 * <p>Usage example:</p>
 * <pre>
 * public class Book {
 *     {@code @GenField("title")}
 *     private String title;
 * }
 * </pre>
 *
 * <p>In the example above, the field {@code title} will be serialized with the name {@code "title"}.</p>
 *
 * <p>Without the {@code value} attribute, the field will be serialized with its original name.</p>
 *
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Target({ FIELD })
@Retention(RUNTIME)
public @interface GenField {
    /**
     * The custom name for the field during serialization. If left empty, the field's name is used.
     *
     * @return the custom name for the field
     */
    String value() default "";
}
