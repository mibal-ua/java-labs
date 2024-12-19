package ua.mibal.serializer;

import ua.mibal.serializer.component.XmlModelValidator;
import ua.mibal.serializer.model.XmlModel;

import java.util.Map;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class XmlSerializer extends Serializer<XmlModel> {
    private final XmlModelValidator validator = new XmlModelValidator();

    @Override
    public String serialize(Object model) {
        return mapToString(serializeModel(model));
    }

    @Override
    public XmlModel serializeModel(Object model) {
        if (model == null) {
            return null;
        }
        validator.validate(model);
        return mapToXml(model);
    }

    private XmlModel mapToXml(Object model) {
        Class<?> clazz = model.getClass();

        ua.mibal.serializer.annotation.XmlModel xmlModelName =
                clazz.getAnnotation(ua.mibal.serializer.annotation.XmlModel.class);
        String modelName = xmlModelName.value().isEmpty()
                ? lowerCaseFirstLetter(clazz.getSimpleName())
                : xmlModelName.value();

        return new XmlModel(modelName, super.map(model));
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
