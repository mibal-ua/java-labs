package ua.mibal.serializer.codegen;

import ua.mibal.codegen.api.GenerateSerializer;
import ua.mibal.codegen.api.GeneratedSerializer;

/**
 * The Serializer interface that will be implemented by a generated class.
 * It extends the {@link GeneratedSerializer} interface with general methods; annotated with 
 * {@link GenerateSerializer} to indicate that a serializer class will be generated.
 *
 * This interface serves as a marker for the code generation process to create 
 * a serializer class with methods for serializing objects.
 *
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@GenerateSerializer
public interface Serializer extends GeneratedSerializer {
}
