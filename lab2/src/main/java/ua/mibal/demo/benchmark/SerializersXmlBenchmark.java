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
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@State(Scope.Thread)
public class SerializersXmlBenchmark {
    private static final Serializer reflection = new Serializer();
    private static final ua.mibal.serializer.codegen.Serializer generated = Serializers.getInstance(ua.mibal.serializer.codegen.Serializer.class);

    private static final Book book = Book.builder()
            .author("Mykhailo Balakhon")
            .title("Java Serialization Benchmark")
            .year(2019)
            .build();

    @Benchmark
    @BenchmarkMode(AverageTime)
    @OutputTimeUnit(MILLISECONDS)
    public void benchmark_reflection() {
        reflection.xml(book);
    }

    @Benchmark
    @BenchmarkMode(AverageTime)
    @OutputTimeUnit(MILLISECONDS)
    public void benchmark_generated() {
        generated.xml(book);
    }
}
