package ua.mibal.serializer.model;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Getter
@Builder
public class XmlModel {
    private final String name;
    private final Map<String, Object> properties;
    
    public XmlModel() {
        this(null, Map.of());
    }

    public XmlModel(String name, Map<String, Object> properties) {
        this.name = name;
        this.properties = new HashMap<>(properties);
    }

    public Object getProperty(String key) {
        return properties.get(key);
    }
}
