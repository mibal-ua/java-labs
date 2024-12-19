package ua.mibal.serializer;

import ua.mibal.serializer.component.XmlModelValidator;
import ua.mibal.serializer.exception.XmlSerializationException;
import ua.mibal.serializer.model.XmlModel;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class XmlSerializer {
    private final XmlModelValidator validator = new XmlModelValidator();

    public String serialize(Object model) {
        return mapToString(serializeModel(model));
    }

    public XmlModel serializeModel(Object model) {
        if (model == null) {
            return null;
        }
        validator.validate(model);
        return map(model);
    }

    private XmlModel map(Object model) {
        Class<?> clazz = model.getClass();

        ua.mibal.serializer.annotation.XmlModel xmlModelName =
                clazz.getAnnotation(ua.mibal.serializer.annotation.XmlModel.class);
        String modelName = xmlModelName.value().isEmpty()
                ? clazz.getSimpleName()
                : xmlModelName.value();
        Map<String, Object> properties = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ua.mibal.serializer.annotation.Field.class)) {
                ua.mibal.serializer.annotation.Field annotation = field.getAnnotation(ua.mibal.serializer.annotation.Field.class);
                String key = annotation.value().isEmpty()
                        ? field.getName()
                        : annotation.value();
                field.setAccessible(true);
                try {
                    Object value = field.get(model);
                    properties.put(key, value);
                } catch (IllegalAccessException e) {
                    throw new XmlSerializationException("Failed to access field: " + field.getName(), e);
                }
            }
        }

        return new XmlModel(modelName, properties);
    }

    private String mapToString(XmlModel xmlModel) {
        if (xmlModel == null) {
            return "";
        }

        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<").append(xmlModel.getName()).append(">");

        for (Map.Entry<String, Object> entry : xmlModel.getProperties().entrySet()) {
            xmlBuilder.append("<").append(entry.getKey()).append(">")
                    .append(entry.getValue())
                    .append("</").append(entry.getKey()).append(">");
        }

        xmlBuilder.append("</").append(xmlModel.getName()).append(">");
        return xmlBuilder.toString();
    }
}
