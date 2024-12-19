package ua.mibal.demo.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import ua.mibal.demo.model.Book;
import ua.mibal.serializer.PlainBookXmlSerializer;
import ua.mibal.serializer.XmlSerializer;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.openjdk.jmh.annotations.Mode.AverageTime;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@State(Scope.Thread)
public class XmlSerializersBenchmark {
    private static final XmlSerializer xmlSerializer = new XmlSerializer();
    private static final PlainBookXmlSerializer plainBookXmlSerializer = new PlainBookXmlSerializer();

    private static final Book book = Book.builder()
            .author("Mykhailo Balakhon")
            .title("Java Serialization Benchmark")
            .year(2019)
            .build();

    @Benchmark
    @BenchmarkMode(AverageTime)
    @OutputTimeUnit(MILLISECONDS)
    public void benchmark_reflection() {
        xmlSerializer.serialize(book);
    }

    @Benchmark
    @BenchmarkMode(AverageTime)
    @OutputTimeUnit(MILLISECONDS)
    public void benchmark_plain() {
        plainBookXmlSerializer.serialize(book);
    }
}
