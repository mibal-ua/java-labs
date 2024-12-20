package ua.mibal.codegen.api;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public interface GeneratedSerializer {
    
    default String json(Object object) {
        throw new IllegalArgumentException("Mapping for this class %s is not declared!".formatted(object.getClass().getName()));
    }

    default String xml(Object object) {
        throw new IllegalArgumentException("Mapping for this class %s is not declared!".formatted(object.getClass().getName()));
    }
}
