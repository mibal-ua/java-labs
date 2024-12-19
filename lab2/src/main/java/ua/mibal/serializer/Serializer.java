package ua.mibal.serializer;

import ua.mibal.serializer.exception.XmlSerializationException;
import ua.mibal.serializer.model.SerializationModel;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public abstract class Serializer<T extends SerializationModel> {

    public abstract String serialize(Object model);

    public abstract T serializeModel(Object model);

    protected SerializationModel map(Object model) {
        Class<?> clazz = model.getClass();
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

        return new SerializationModel(properties);
    }
}
