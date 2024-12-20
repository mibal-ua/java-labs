package ua.mibal.codegen.util;

import ua.mibal.codegen.api.GenField;

import javax.lang.model.element.Element;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class ReflectionUtils {

    public static Map<Element, List<? extends Element>> getFieldMappings(Set<? extends Element> models) {
        Map<Element, List<? extends Element>> properties = new HashMap<>();
        
        for (Element model : models) {
            List<? extends Element> fields = model.getEnclosedElements().stream()
                    .filter(e -> e.getKind().isField())
                    .filter(e -> e.getAnnotation(GenField.class) != null)
                    .toList();
            properties.put(model, fields);
        }

        return properties;
    }

    public static String getGetterName(Element field) {
        String fieldName = field.getSimpleName().toString();
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public static String getPropertyName(Element field) {
        GenField annotation = field.getAnnotation(GenField.class);
        return annotation.value().isEmpty()
                ? field.getSimpleName().toString()
                : annotation.value();
    }

    public static String extractClassName(Element model) {
        String className = model.getSimpleName().toString();
        String packageName = model.getEnclosingElement().toString();
        return packageName.isEmpty()
                ? className
                : packageName + "." + className;
    }
}
