package ua.mibal.codegen.api;

/**
 * Utility class to get instance of generated serializer implementations.
 *
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class Serializers {

    /**
     * Retrieves an instance of the generated serializer implementation for the specified mapper class.
     *
     * @param mapperClass the interface of the serializer to be instantiated
     * @param <T> the type of the serializer
     * @return an instance of the generated serializer implementation
     * @throws RuntimeException if there was an issue instantiating the serializer class
     */
    public static <T> T getInstance(Class<T> mapperClass) {
        try {
            String implClassName = mapperClass.getName() + "Impl";
            return (T) Class.forName(implClassName).newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Serializer implementation not found for: " + mapperClass.getName(), e);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Failed to instantiate serializer implementation for: " + mapperClass.getName(), e);
        }
    }
}
