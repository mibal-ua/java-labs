package ua.mibal.serializer.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The annotation is used to mark a field that should be serialized to XML.
 * 
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Target({ FIELD })
@Retention(RUNTIME)
public @interface Field {

    /**
     * The name of the field in the XML.
     * 
     * @return the name of the field in the XML
     */
    String value() default "";
}
