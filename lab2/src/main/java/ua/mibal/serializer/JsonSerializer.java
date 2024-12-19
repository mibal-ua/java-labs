package ua.mibal.serializer;

import ua.mibal.serializer.component.ModelValidator;
import ua.mibal.serializer.model.JsonModel;

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
        //todo
        return "";
    }
}
