package ua.mibal.serializer.component;

import ua.mibal.serializer.exception.SerializationException;

import java.io.Serializable;

import static java.util.Arrays.stream;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class ModelValidator {

    public void validate(Object model) {
        validateSerializableInterface(model);
    }

    private void validateSerializableInterface(Object model) {
        Class<?>[] interfaces = model.getClass().getInterfaces();
        if (stream(interfaces).noneMatch(
                c -> c.equals(Serializable.class))
        ) {
            throw new SerializationException(
                    "Model can not be serialized. " +
                    "Model class should implement Serializable interface"
            );
        }
    }
}
