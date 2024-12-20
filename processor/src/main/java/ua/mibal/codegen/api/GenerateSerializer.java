package ua.mibal.codegen.api;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Annotation used to mark classes for code generation of serializers.
 * The {@code GenerateSerializer} annotation indicates that a serializer implementation
 * should be generated for the annotated class by a code generation tool.
 *
 * <p>This annotation is typically used in conjunction with the {@link Serialize} and {@link GenField} annotations.</p>
 *
 * <p>When applied to a class, the code generator will automatically create an implementation of the serializer,
 * which includes methods for serializing the class to formats like JSON or XML.</p>
 *
 * <p>Note: This annotation does not directly generate code but signals to a code processor or tool to perform
 * the code generation during compile-time.</p>
 *
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Target(TYPE)
@Retention(SOURCE)
public @interface GenerateSerializer {
}
