package ua.mibal.serializer;

import ua.mibal.serializer.component.ModelValidator;
import ua.mibal.serializer.model.JsonModel;

import java.util.Map;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class JsonSerializer extends Serializer<JsonModel> {
    private final ModelValidator validator = new ModelValidator();
    
    @Override
    public String serialize(Object model) {
        return mapToString(serializeModel(model));
    }

    @Override
    public JsonModel serializeModel(Object model) {
        if (model == null) {
            return null;
        }
        validator.validate(model);
        return mapToJson(model);
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
}
