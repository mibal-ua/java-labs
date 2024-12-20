package ua.mibal.demo;

import lombok.extern.slf4j.Slf4j;
import ua.mibal.codegen.api.Serializers;
import ua.mibal.demo.model.Book;
import ua.mibal.demo.model.Car;
import ua.mibal.demo.model.NonSerializable;
import ua.mibal.demo.model.Transaction;
import ua.mibal.serializer.codegen.Serializer;

/**
 * Entry point of the application that demonstrates serialization of different objects into XML and JSON formats.
 * The application creates instances of various models and serializes them using the `Serializer` class.
 *
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Slf4j
public class Launcher {
    // Instance of the Serializer to perform serialization tasks
    private static final Serializer serializer = Serializers.getInstance(Serializer.class);

    /**
     * The main entry point of the application. It creates instances of Book, Car, and Transaction,
     * serializes them to XML and JSON formats, and handles any serialization errors for non-serializable objects.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // Create and serialize a Book object
        Book cleanCode = Book.builder()
                .title("Clean Code")
                .author("Robert C. Martin")
                .year(2008)
                .build();
        serialize(cleanCode);

        // Create and serialize a Car object
        Car camry = Car.builder()
                .brand("Toyota")
                .model("Camry")
                .year(2018)
                .build();
        serialize(camry);

        // Create and serialize a Transaction object
        Transaction winterESupport = Transaction.builder()
                .id("ldfsjasj-1234-1234-1234-1234")
                .secret("secret-key")
                .sender("zelia@email.com")
                .recipient("ya@email.com")
                .amount(1000)
                .currency("UAH")
                .build();
        serialize(winterESupport);

        // Attempt to serialize a NonSerializable object and handle the expected error
        try {
            NonSerializable nonSerializable = NonSerializable.builder()
                    .name("NonSerializable")
                    .build();
            serialize(nonSerializable);
        } catch (Exception e) {
            log.error("Keep calm, expected error during serialization", e);
        }
    }

    /**
     * Serializes the given object to both XML and JSON formats and logs the results.
     *
     * @param object the object to be serialized
     */
    private static void serialize(Object object) {
        String className = object.getClass().getSimpleName();
        log.info("{} serialization demo", className);
        log.info("{}: {}", className, object);

        // Serialize object to XML format
        String xml = serializer.xml(object);
        log.info("{}: {}", className, xml);

        // Serialize object to JSON format
        String json = serializer.json(object);
        log.info("{}: {}", className, json);

        log.info(""); // Add a blank line for separation
    }
}
