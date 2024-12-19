package ua.mibal.demo;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ua.mibal.demo.benchmark.JsonSerializersBenchmark;
import ua.mibal.demo.benchmark.SerializersXmlBenchmark;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Slf4j
public class BenchmarkLauncher {

    public static void main(String[] args) {
        run(SerializersXmlBenchmark.class);
        run(JsonSerializersBenchmark.class);
    }

    private static void run(Class<?> benchmark) {
        try {
            Options opt = new OptionsBuilder()
                    .include(benchmark.getSimpleName())
                    .forks(1)
                    .build();

            new Runner(opt).run();
        } catch (RunnerException e) {
            log.error("Failed to run benchmark", e);
        }
    }
}
