package ua.mibal.serializer.component;

import ua.mibal.serializer.annotation.XmlModel;
import ua.mibal.serializer.exception.SerializationException;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class XmlModelValidator extends ModelValidator {

    public void validate(Object model) {
        validateXmlModelAnnotation(model);
        super.validate(model);
    }

    private void validateXmlModelAnnotation(Object model) {
        XmlModel xmlModel = model.getClass().getAnnotation(XmlModel.class);
        if (xmlModel == null) {
            throw new SerializationException(
                    "Model can not be serialized. " +
                    "Model class should be annotated with " + XmlModel.class.getSimpleName() + " annotation"
            );
        }
    }
}
