package ua.mibal.serializer;

import lombok.Builder;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.xmlunit.assertj3.XmlAssert;
import ua.mibal.serializer.annotation.Field;
import ua.mibal.serializer.exception.SerializationException;
import ua.mibal.serializer.model.JsonModel;
import ua.mibal.serializer.model.XmlModel;

import java.io.Serializable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class SerializerTest {
    private final Serializer serializer = new Serializer();

    @Test
    void xmlModel_null() {
        XmlModel actual = serializer.xmlModel(null);

        assertThat(actual).isNull();
    }

    @Test
    void xmlModel_notSerializable() {
        assertThrows(SerializationException.class,
                () -> serializer.xmlModel(new TestNotSerializable()));
    }

    @Test
    void xmlModel_notAnXmlModel() {
        assertThrows(SerializationException.class,
                () -> serializer.xmlModel(new TestNotXmlModel()));
    }

    @Test
    void xmlModel() {
        TestClass testClass = TestClass.builder()
                .name("John")
                .age(25)
                .isMale(true)
                .build();

        XmlModel actual = serializer.xmlModel(testClass);

        assertThat(actual).isNotNull();
        assertThat(actual.getName()).isEqualTo("test");
        assertThat(actual.getProperty("name")).isEqualTo("John");
        assertThat(actual.getProperty("age")).isEqualTo(25);
        assertThat(actual.getProperty("male")).isEqualTo(true);
        assertThat(actual.getProperty("hidden")).isNull();
    }

    @Test
    void xml() {
        TestClass testClass = TestClass.builder()
                .name("John")
                .age(25)
                .isMale(true)
                .build();

        String actual = serializer.xml(testClass);

        assertThat(actual).isNotNull();
        XmlAssert.assertThat(actual).nodesByXPath("test").exist();
        XmlAssert.assertThat(actual).valueByXPath("test/hidden").isBlank();
        XmlAssert.assertThat(actual).valueByXPath("test/name").isEqualTo("John");
        XmlAssert.assertThat(actual).valueByXPath("test/age").isEqualTo(25);
        XmlAssert.assertThat(actual).valueByXPath("test/male").isEqualTo(true);
    }


    @Test
    void jsonModel_null() {
        JsonModel actual = serializer.jsonModel(null);

        assertThat(actual).isNull();
    }

    @Test
    void jsonModel_notSerializable() {
        assertThrows(SerializationException.class,
                () -> serializer.jsonModel(new TestNotSerializable()));
    }

    @Test
    void jsonModel() {
        TestClass testClass = TestClass.builder()
                .name("John")
                .age(25)
                .isMale(true)
                .build();

        JsonModel actual = serializer.jsonModel(testClass);

        assertThat(actual).isNotNull();
        assertThat(actual.getProperty("name")).isEqualTo("John");
        assertThat(actual.getProperty("age")).isEqualTo(25);
        assertThat(actual.getProperty("male")).isEqualTo(true);
        assertThat(actual.getProperty("hidden")).isNull();
    }

    @Test
    void json() throws JSONException {
        TestClass testClass = TestClass.builder()
                .name("John")
                .age(25)
                .isMale(true)
                .build();

        String actual = serializer.json(testClass);

        assertThat(actual).isNotNull();

        JSONAssert.assertEquals(
                """
                        {
                          "name": "John",
                          "age": 25,
                          "male": true
                        }""",
                actual, STRICT
        );
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
        private boolean hidden;
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
