package ua.mibal.serializer.reflection.model;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@ToString
@Getter
public class XmlModel extends SerializationModel {
    private final String name;

    public XmlModel() {
        this(null, Map.of());
    }

    public XmlModel(String name, SerializationModel model) {
        this(name, model.properties);
    }

    public XmlModel(String name, Map<String, Object> properties) {
        super(properties);
        this.name = name;
    }
}
