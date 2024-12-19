package ua.mibal.serializer.component;

import ua.mibal.serializer.annotation.XmlModel;
import ua.mibal.serializer.exception.XmlSerializationException;

import java.io.Serializable;

import static java.util.Arrays.stream;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class XmlModelValidator {

    public void validate(Object model) {
        validateXmlModelAnnotation(model);
        validateSerializableInterface(model);
    }

    private void validateXmlModelAnnotation(Object model) {
        XmlModel xmlModel = model.getClass().getAnnotation(XmlModel.class);
        if (xmlModel == null) {
            throw new XmlSerializationException(
                    "Model can not be serialized. " +
                    "Model class should be annotated with " + XmlModel.class.getSimpleName() + " annotation"
            );
        }
    }

    private void validateSerializableInterface(Object model) {
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
