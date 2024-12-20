package ua.mibal.codegen;

import ua.mibal.codegen.api.GenerateSerializer;
import ua.mibal.codegen.api.Serialize;
import ua.mibal.codegen.util.ReflectionUtils;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static javax.lang.model.SourceVersion.RELEASE_17;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@SupportedAnnotationTypes({
        "ua.mibal.codegen.api.GenerateSerializer",
        "ua.mibal.codegen.api.Serialize"
})
@SupportedSourceVersion(RELEASE_17)
public class MyProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations,
                           RoundEnvironment roundEnv) {
        Set<? extends Element> models =
                roundEnv.getElementsAnnotatedWith(Serialize.class);
        roundEnv.getElementsAnnotatedWith(GenerateSerializer.class).forEach(serializer ->
                generateStubSerializerFor(serializer, models)
        );
        return true;
    }

    private void generateStubSerializerFor(Element serializer, Set<? extends Element> models) {
        String serializerIClassName = serializer.getSimpleName().toString();
        String serializerImplName = serializerIClassName + "Impl";
        String packageName = serializer.getEnclosingElement().toString();

        Map<Element, List<? extends Element>> fieldsOfModels = ReflectionUtils.getFieldMappings(models);

        try (PrintWriter writer = new PrintWriter(
                processingEnv.getFiler().createSourceFile(packageName + "." + serializerImplName).openWriter())) {
            writer.println("""
                    package %s;
                    
                    import %s;
                    
                    /**
                     * Autogenerated Serializer
                     *
                     * @author Mykhailo Balakhon
                     * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
                     */
                    public class %s implements %s {
                    """.formatted(packageName, packageName + "." + serializerIClassName, serializerImplName, serializerIClassName)
            );
            
            generateJsonRouter(writer, fieldsOfModels);
            generateXmlRouter(writer, fieldsOfModels);

            fieldsOfModels.forEach((className, fields) -> {
                writer.println(generateJsonMethod(className, fields));
                writer.println(generateXmlMethod(className, fields));
            });

            writer.println("}\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateJsonRouter(PrintWriter writer, Map<Element, List<? extends Element>> fieldsOfModels) {
        writer.println("\tpublic String json(Object object) {");
        fieldsOfModels.forEach((element, fields) -> {
            String className = ReflectionUtils.extractClassName(element);
            writer.println(("\t\tif (object instanceof %s) {\n" +
                            "\t\t\treturn mapJson((%s) object);\n" +
                            "\t\t}").formatted(className, className)
            );
        });
        writer.println("\t\tthrow new IllegalArgumentException(\"Mapping for this class \" + object.getClass().getName() + \" is not declared!\");\n" +
                       "\t}\n");
    }

    private void generateXmlRouter(PrintWriter writer, Map<Element, List<? extends Element>> fieldsOfModels) {
        writer.println("\tpublic String xml(Object object) {");
        fieldsOfModels.forEach((element, fields) -> {
            String className = ReflectionUtils.extractClassName(element);
            writer.println(("\t\tif (object instanceof %s) {\n" +
                            "\t\t\treturn mapXml((%s) object);\n" +
                            "\t\t}").formatted(className, className)
            );
        });
        writer.println("\t\tthrow new IllegalArgumentException(\"Mapping for this class \" + object.getClass().getName() + \" is not declared!\");\n" +
                       "\t}\n");
    }

    private String generateJsonMethod(Element clazz, List<? extends Element> fields) {
        StringBuilder methodBuilder = new StringBuilder();

        String className = ReflectionUtils.extractClassName(clazz);
        methodBuilder.append("\tprivate String mapJson(%s o) {\n".formatted(className));
        methodBuilder.append("\t\treturn \"{\" +\n");

        for (Element field : fields) {
            String getter = ReflectionUtils.getGetterName(field);
            String propertyName = ReflectionUtils.getPropertyName(field);
            String fieldType = field.asType().toString();

            if (String.class.getName().equals(fieldType)) {
                methodBuilder.append("""
                    \t\t\t"\\"%s\\": \\"" + o.%s() + "\\"" + "," +
                    """.formatted(propertyName, getter));
            } else {
                methodBuilder.append("""
                    \t\t\t"\\"%s\\": " + o.%s() + "," +
                    """.formatted(propertyName, getter));
            }
        }
        methodBuilder.delete(methodBuilder.length() - 6, methodBuilder.length() - 1);
        methodBuilder.append("\t\t\"}\";\n");
        methodBuilder.append("\t}\n");
        return methodBuilder.toString();
    }

    private String generateXmlMethod(Element clazz, List<? extends Element> fields) {

        StringBuilder methodBuilder = new StringBuilder();
        String className = ReflectionUtils.extractClassName(clazz);

        Serialize xmlModelName = clazz.getAnnotation(Serialize.class);
        String modelName = xmlModelName.value().isEmpty()
                ? lowerCaseFirstLetter(clazz.getSimpleName().toString())
                : xmlModelName.value();

        methodBuilder.append("\tprivate String mapXml(%s o) {\n".formatted(className));
        methodBuilder.append("\t\treturn \"<%s>\" +\n".formatted(modelName));

        for (Element field : fields) {
            String getter = ReflectionUtils.getGetterName(field);
            String propertyName = ReflectionUtils.getPropertyName(field);

            methodBuilder.append("""
                    \t\t\t"<%s>" + o.%s() + "</%s>" +
                    """.formatted(propertyName, getter, propertyName));
        }
        methodBuilder.append("\t\t\t \"</%s>\";\n".formatted(modelName));
        methodBuilder.append("\t}\n");
        return methodBuilder.toString();
    }

    private String lowerCaseFirstLetter(String simpleName) {
        return simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
    }
}
