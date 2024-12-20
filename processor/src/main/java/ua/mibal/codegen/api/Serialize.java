package ua.mibal.codegen.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for marking classes to be serialized, allowing customization of the model name
 * in XML or other formats.
 *
 * <p>This annotation is typically used in conjunction with code generation tools to customize
 * the serialization behavior of annotated classes. By default, the class name is used as
 * the name for the model during serialization. However, this annotation allows the name
 * to be customized, especially for XML serialization where the model name corresponds to
 * the element name in the XML structure.</p>
 *
 * <p>If the {@code value} attribute is provided, it will be used as the name for the serialized
 * model. If not, the default behavior is to use the lowercase class name.</p>
 *
 * <p>Example usage:</p>
 *
 * <pre>
 * {@code
 * @Serialize("book")
 * public class Book {
 *     private String title;
 *     private String author;
 *     private int year;
 * }
 * }
 * </pre>
 *
 * <p>In this example, the {@code Book} class will be serialized with the XML element name "book"
 * instead of the default "book" (lowercase class name). If the {@code value} attribute is not provided,
 * the default would be to serialize as "<book>" in XML.</p>
 *
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Serialize {

    /**
     * Specifies the name to be used for the model during serialization (e.g., XML element name).
     * If left empty, the default is the lowercase class name.
     *
     * @return the name of the XML element or model representation
     */
    String value() default "";
}
