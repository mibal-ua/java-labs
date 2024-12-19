package ua.mibal.serializer;

import ua.mibal.serializer.exception.XmlSerializationException;
import ua.mibal.serializer.model.XmlModel;

import java.io.Serializable;

import static java.util.Arrays.stream;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class XmlSerializer {

    public String serialize(Object model) {
        return mapToString(serializeModel(model));
    }

    public XmlModel serializeModel(Object model) {
        if (model == null) {
            return null;
        }
        checkSupported(model);
        return map(model);
    }

    private XmlModel map(Object model) {
        //todo
        return null;
    }

    private String mapToString(XmlModel xmlModel) {
        //todo
        return null;
    }

    private void checkSupported(Object model) {
        Class<?>[] interfaces = model.getClass().getInterfaces();
        if (stream(interfaces).noneMatch(
                c -> c.equals(Serializable.class))
        ) {
            throw new XmlSerializationException(
                    "Model can not be serialized. " +
                    "Model class should implement Serializable interface"
            );
        }
    }
}
