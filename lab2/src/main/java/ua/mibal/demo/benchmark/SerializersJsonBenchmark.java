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
 * This class benchmarks the performance of JSON serialization. It compares two different serialization methods:
 * reflection-based serialization and generated code-based serialization.
 *
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@State(Scope.Thread)
public class SerializersJsonBenchmark {

    // Serializer using reflection-based method
    private static final Serializer reflection = new Serializer();

    // Serializer using generated code-based method
    private static final ua.mibal.serializer.codegen.Serializer generated = Serializers.getInstance(ua.mibal.serializer.codegen.Serializer.class);

    // Sample Book object to be serialized
    private static final Book book = Book.builder()
            .author("Mykhailo Balakhon")
            .title("Java Serialization Benchmark")
            .year(2019)
            .build();

    /**
     * Benchmark method that measures the average time taken for JSON serialization using reflection-based serialization.
     *
     * @return the time taken to serialize the Book object into JSON using reflection-based method
     */
    @Benchmark
    @BenchmarkMode(AverageTime)
    @OutputTimeUnit(MILLISECONDS)
    public void benchmark_reflection() {
        reflection.json(book);
    }

    /**
     * Benchmark method that measures the average time taken for JSON serialization using generated code-based serialization.
     *
     * @return the time taken to serialize the Book object into JSON using generated code-based method
     */
    @Benchmark
    @BenchmarkMode(AverageTime)
    @OutputTimeUnit(MILLISECONDS)
    public void benchmark_generated() {
        generated.json(book);
    }
}
