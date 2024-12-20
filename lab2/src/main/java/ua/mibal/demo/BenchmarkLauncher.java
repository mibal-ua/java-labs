package ua.mibal.demo;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ua.mibal.demo.benchmark.SerializersJsonBenchmark;
import ua.mibal.demo.benchmark.SerializersXmlBenchmark;

/**
 * BenchmarkLauncher is responsible for running performance benchmarks for different serializer classes.
 * It launches the benchmarks for XML and JSON serializers using JMH (Java Microbenchmarking Harness).
 *
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Slf4j
public class BenchmarkLauncher {

    /**
     * The main entry point of the BenchmarkLauncher. It runs benchmarks for XML and JSON serializers.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        run(SerializersXmlBenchmark.class);
        run(SerializersJsonBenchmark.class);
    }

    /**
     * Runs a specific benchmark class using JMH.
     *
     * @param benchmark the benchmark class to be executed
     */
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
