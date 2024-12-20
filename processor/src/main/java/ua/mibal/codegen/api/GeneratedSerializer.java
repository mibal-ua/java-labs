package ua.mibal.codegen.api;

/**
 * Interface that defines the contract for generated serializer classes.
 *
 * <p>Classes implementing this interface are expected to provide serialization
 * logic for converting objects into JSON and XML formats. This interface is
 * typically implemented by code generation tools that auto-generate the serializer
 * code for a given class.</p>
 *
 * <p>Any class that is annotated with {@link GenerateSerializer} will have a corresponding
 * implementation of this interface generated during the build process, allowing the 
 * objects of that class to be serialized into different formats (e.g., JSON, XML).</p>
 *
 * <p>The generated methods {@code json()} and {@code xml()} should handle the conversion
 * of objects to their respective formats based on the class's fields and annotations
 * (such as {@link Serialize} and {@link GenField}).</p>
 *
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public interface GeneratedSerializer {

    /**
     * Serializes the given object to a JSON string.
     *
     * @param object the object to be serialized
     * @return a JSON representation of the object
     */
    String json(Object object);

    /**
     * Serializes the given object to an XML string.
     *
     * @param object the object to be serialized
     * @return an XML representation of the object
     */
    String xml(Object object);
}
