package ua.mibal.serializer.reflection.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation for marking classes that should be serialized to XML.
 * 
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Target({ TYPE })
@Retention(RUNTIME)
public @interface XmlModel {

    /**
     * @return the name of the XML element
     */
    String value() default "";
}
