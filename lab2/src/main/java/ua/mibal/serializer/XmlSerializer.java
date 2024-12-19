package ua.mibal.serializer;

import ua.mibal.serializer.component.XmlModelValidator;
import ua.mibal.serializer.model.XmlModel;

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
        //todo
        return null;
    }

    private String mapToString(XmlModel xmlModel) {
        //todo
        return null;
    }
}
