package ua.mibal.serializer.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class XmlModel extends HashMap<String, Object> {

    public XmlModel(Map<String, Object> source) {
        super(source);
    }

    public static XmlModel of(Map<String, Object> source) {
        return new XmlModel(source);
    }
}
