package ua.mibal.demo;

import lombok.extern.slf4j.Slf4j;
import ua.mibal.demo.model.Book;
import ua.mibal.demo.model.Car;
import ua.mibal.demo.model.NonSerializable;
import ua.mibal.demo.model.Transaction;
import ua.mibal.serializer.Serializer;
import ua.mibal.serializer.exception.SerializationException;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Slf4j
public class Launcher {
    private static final Serializer serializer = new Serializer();

    public static void main(String[] args) {
        Book cleanCode = Book.builder()
                .title("Clean Code")
                .author("Robert C. Martin")
                .year(2008)
                .build();
        serialize(cleanCode);

        Car camry = Car.builder()
                .brand("Toyota")
                .model("Camry")
                .year(2018)
                .build();
        serialize(camry);

        Transaction winterESupport = Transaction.builder()
                .id("ldfsjasj-1234-1234-1234-1234")
                .secret("secret-key")
                .sender("zelia@email.com")
                .recipient("ya@email.com")
                .amount(1000)
                .currency("UAH")
                .build();
        serialize(winterESupport);

        try {
            NonSerializable nonSerializable = NonSerializable.builder()
                    .name("NonSerializable")
                    .build();
            serialize(nonSerializable);
        } catch (SerializationException e) {
            log.error("Keep calm, expected error during serialization", e);
        }
    }
    
    private static void serialize(Object object) {
        String className = object.getClass().getSimpleName();
        log.info("{} serialization demo", className);
        log.info("{}: {}", className, object);

        String xml = serializer.xml(object);
        log.info("{}: {}", className, xml);
        
        String json = serializer.json(object);
        log.info("{}: {}", className, json);
        
        log.info("");
    }
}
