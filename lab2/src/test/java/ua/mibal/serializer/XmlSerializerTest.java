package ua.mibal.serializer;

import lombok.Builder;
import org.junit.jupiter.api.Test;
import ua.mibal.serializer.annotation.Field;
import ua.mibal.serializer.exception.XmlSerializationException;
import ua.mibal.serializer.model.XmlModel;

import java.io.Serializable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class XmlSerializerTest {
    private final XmlSerializer serializer = new XmlSerializer();

    @Test
    void serializeModel_null() {
        XmlModel actual = serializer.serializeModel(null);

        assertThat(actual).isNull();
    }

    @Test
    void serializeModel_notSerializable() {
        assertThrows(XmlSerializationException.class,
                () -> serializer.serializeModel(new TestNotSerializable()));
    }

    @Test
    void serializeModel() {
        TestClass testClass = TestClass.builder()
                .name("John")
                .age(25)
                .isMale(true)
                .build();

        XmlModel actual = serializer.serializeModel(testClass);
        
        assertThat(actual).isNotNull();
        assertThat(actual.get("name")).equals("John");
        assertThat(actual.get("age")).equals(25);
        assertThat(actual.get("isMale")).equals(true);
    }

    @Builder
    private static class TestClass implements Serializable {
        @Field
        private String name;
        @Field
        private int age;
        @Field
        private boolean isMale;
    }

    private static class TestNotSerializable {
        @Field
        private String name;
        @Field
        private int age;
        @Field
        private boolean isMale;
    }
}
