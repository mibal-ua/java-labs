package ua.mibal.serializer.reflection.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Getter
public class SerializationModel {
    protected final Map<String, Object> properties;

    public SerializationModel(Map<String, Object> properties) {
        this.properties = new HashMap<>(properties);
    }

    public Object getProperty(String key) {
        return properties.get(key);
    }
}
