package ua.mibal.serializer.reflection;

import ua.mibal.serializer.reflection.component.ModelValidator;
import ua.mibal.serializer.reflection.component.XmlModelValidator;
import ua.mibal.serializer.reflection.exception.SerializationException;
import ua.mibal.serializer.reflection.model.JsonModel;
import ua.mibal.serializer.reflection.model.SerializationModel;
import ua.mibal.serializer.reflection.model.XmlModel;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class Serializer {
    private final XmlModelValidator xmlValidator = new XmlModelValidator();
    private final ModelValidator jsonValidator = new ModelValidator();

    public String xml(Object model) {
        return mapToString(xmlModel(model));
    }

    public XmlModel xmlModel(Object model) {
        if (model == null) {
            return null;
        }
        xmlValidator.validate(model);
        return mapToXml(model);
    }

    public JsonModel jsonModel(Object model) {
        if (model == null) {
            return null;
        }
        jsonValidator.validate(model);
        return mapToJson(model);
    }

    public String json(Object model) {
        return mapToString(jsonModel(model));
    }

    protected SerializationModel map(Object model) {
        Class<?> clazz = model.getClass();
        Map<String, Object> properties = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ua.mibal.serializer.reflection.annotation.Field.class)) {
                ua.mibal.serializer.reflection.annotation.Field annotation = field.getAnnotation(ua.mibal.serializer.reflection.annotation.Field.class);
                String key = annotation.value().isEmpty()
                        ? field.getName()
                        : annotation.value();
                field.setAccessible(true);
                try {
                    Object value = field.get(model);
                    properties.put(key, value);
                } catch (IllegalAccessException e) {
                    throw new SerializationException("Failed to access field: " + field.getName(), e);
                }
            }
        }

        return new SerializationModel(properties);
    }

    private JsonModel mapToJson(Object model) {
        return new JsonModel(map(model).getProperties());
    }

    private String mapToString(JsonModel jsonModel) {
        Map<String, Object> properties = jsonModel.getProperties();
        StringBuilder builder = new StringBuilder();

        builder.append("{");
        properties.forEach((key, value) -> {
            builder.append("\"").append(key).append("\":");
            if (value instanceof String) {
                builder.append("\"").append(value).append("\"");
            } else {
                builder.append(value);
            }
            builder.append(",");
        });
        builder.deleteCharAt(builder.length() - 1);
        builder.append("}");

        return builder.toString();
    }

    private XmlModel mapToXml(Object model) {
        Class<?> clazz = model.getClass();

        ua.mibal.serializer.reflection.annotation.XmlModel xmlModelName =
                clazz.getAnnotation(ua.mibal.serializer.reflection.annotation.XmlModel.class);
        String modelName = xmlModelName.value().isEmpty()
                ? lowerCaseFirstLetter(clazz.getSimpleName())
                : xmlModelName.value();

        return new XmlModel(modelName, map(model));
    }

    private String lowerCaseFirstLetter(String simpleName) {
        return simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
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
