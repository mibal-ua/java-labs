package ua.mibal.serializer;

import lombok.Builder;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import ua.mibal.serializer.annotation.Field;
import ua.mibal.serializer.exception.SerializationException;
import ua.mibal.serializer.model.JsonModel;

import java.io.Serializable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;


/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class JsonSerializerTest {
    private final JsonSerializer serializer = new JsonSerializer();

    @Test
    void serializeModel_null() {
        JsonModel actual = serializer.serializeModel(null);

        assertThat(actual).isNull();
    }

    @Test
    void serializeModel_notSerializable() {
        assertThrows(SerializationException.class,
                () -> serializer.serializeModel(new TestNotSerializable()));
    }

    @Test
    void serializeModel() {
        TestClass testClass = TestClass.builder()
                .name("John")
                .age(25)
                .isMale(true)
                .build();

        JsonModel actual = serializer.serializeModel(testClass);

        assertThat(actual).isNotNull();
        assertThat(actual.getProperty("name")).isEqualTo("John");
        assertThat(actual.getProperty("age")).isEqualTo(25);
        assertThat(actual.getProperty("male")).isEqualTo(true);
        assertThat(actual.getProperty("hidden")).isNull();
    }

    @Test
    void serialize() throws JSONException {
        TestClass testClass = TestClass.builder()
                .name("John")
                .age(25)
                .isMale(true)
                .build();

        String actual = serializer.serialize(testClass);

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
    private static class TestClass implements Serializable {
        @Field
        private String name;
        @Field
        private int age;
        @Field("male")
        private boolean isMale;
        private boolean hidden;
    }

    private static class TestNotSerializable {
        @Field
        private String name;
    }
}
