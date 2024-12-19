package ua.mibal;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.joining;
import static javax.lang.model.SourceVersion.RELEASE_17;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@SupportedAnnotationTypes("ua.mibal.MyAnnotation")
@SupportedSourceVersion(RELEASE_17)
public class MyProcessor extends AbstractProcessor {

    public MyProcessor() {
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        annotations.forEach(annotation ->
                roundEnv.getElementsAnnotatedWith(annotation)
                        .forEach(this::generateSerializer)
        );
        return true;
    }

    private void generateSerializer(Element element) {
        String className = element.getSimpleName().toString();
        String packageName = element.getEnclosingElement().toString();
        String builderName = className + "ua.mibal.MyAnnotation";
        String builderFullName = packageName + "." + builderName;
        List<? extends Element> fields = element.getEnclosedElements()
                .stream().filter(e -> ElementKind.FIELD.equals(e.getKind())).toList();

        try (PrintWriter writer = new PrintWriter(
                processingEnv.getFiler().createSourceFile(builderFullName).openWriter())) {
            writer.println("""
                    package %s;
                    
                    public class %s {
                    """
                    .formatted(packageName, builderName)
            );

            fields.forEach(field ->
                    writer.print("""
                                private %s %s;
                            """.formatted(field.asType().toString(), field.getSimpleName())
                    )
            );

            writer.println();
            fields.forEach(field ->
                    writer.println("""
                                public %s %s(%s value) {
                                    %s = value;
                                    return this;
                                }
                            """.formatted(builderName, field.getSimpleName(),
                            field.asType().toString(), field.getSimpleName())
                    )
            );

            writer.println("""
                        public %s build() {
                            return new %s(%s);
                        }
                    """.formatted(className, className,
                    fields.stream().map(Element::getSimpleName).collect(joining(", ")))
            );
            writer.println("}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
