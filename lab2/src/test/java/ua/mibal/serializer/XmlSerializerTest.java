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
    void serializeModel_notAnXmlModel() {
        assertThrows(XmlSerializationException.class,
                () -> serializer.serializeModel(new TestNotXmlModel()));
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
        assertThat(actual.getName()).isEqualTo("test");
        assertThat(actual.getProperty("name")).isEqualTo("John");
        assertThat(actual.getProperty("age")).isEqualTo(25);
        assertThat(actual.getProperty("male")).isEqualTo(true);
    }

    @Builder
    @ua.mibal.serializer.annotation.XmlModel("test")
    private static class TestClass implements Serializable {
        @Field
        private String name;
        @Field
        private int age;
        @Field("male")
        private boolean isMale;
    }

    @ua.mibal.serializer.annotation.XmlModel("lol123")
    private static class TestNotSerializable {
        @Field
        private String name;
    }

    private static class TestNotXmlModel implements Serializable {
        @Field
        private String name;
    }
}
