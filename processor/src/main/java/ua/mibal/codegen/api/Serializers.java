package ua.mibal.codegen.api;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class Serializers {

    public static <T> T getInstance(Class<T> mapperClass) {
        try {
            return (T) Class.forName(mapperClass.getName() + "Impl").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
