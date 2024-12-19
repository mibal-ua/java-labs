package ua.mibal.serializer.reflection.model;

import java.util.Map;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class JsonModel extends SerializationModel {
    public JsonModel(Map<String, Object> properties) {
        super(properties);
    }
}
