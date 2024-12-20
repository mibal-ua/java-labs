package ua.mibal.codegen.api;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public interface GeneratedSerializer {

    String json(Object object);

    String xml(Object object);
}
