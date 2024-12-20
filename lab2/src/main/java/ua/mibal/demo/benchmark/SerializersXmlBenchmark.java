package ua.mibal.demo.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import ua.mibal.codegen.api.Serializers;
import ua.mibal.demo.model.Book;
import ua.mibal.serializer.reflection.Serializer;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.openjdk.jmh.annotations.Mode.AverageTime;

/**
 * This class contains benchmarks for XML serialization. It compares the performance 
 * of two serialization methods: reflection-based and generated code-based serialization.
 *
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@State(Scope.Thread)
public class SerializersXmlBenchmark {

    // Serializer using reflection
    private static final Serializer reflection = new Serializer();

    // Serializer using generated code
    private static final ua.mibal.serializer.codegen.Serializer generated = Serializers.getInstance(ua.mibal.serializer.codegen.Serializer.class);

    // Sample Book object to be serialized
    private static final Book book = Book.builder()
            .author("Mykhailo Balakhon")
            .title("Java Serialization Benchmark")
            .year(2019)
            .build();

    /**
     * Benchmark method for measuring the average time of XML serialization using reflection-based serializer.
     *
     * @return the time taken to serialize the book object to XML using reflection
     */
    @Benchmark
    @BenchmarkMode(AverageTime)
    @OutputTimeUnit(MILLISECONDS)
    public void benchmark_reflection() {
        reflection.xml(book);
    }

    /**
     * Benchmark method for measuring the average time of XML serialization using generated code-based serializer.
     *
     * @return the time taken to serialize the book object to XML using generated code
     */
    @Benchmark
    @BenchmarkMode(AverageTime)
    @OutputTimeUnit(MILLISECONDS)
    public void benchmark_generated() {
        generated.xml(book);
    }
}
