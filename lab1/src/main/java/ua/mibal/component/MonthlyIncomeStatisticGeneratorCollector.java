package ua.mibal.component;

import ua.mibal.model.MonthlyIncomeStatistics;
import ua.mibal.model.Participant;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class MonthlyIncomeStatisticGeneratorCollector implements Collector<Participant, MonthlyIncomeStatistics, MonthlyIncomeStatistics> {
// todo
    @Override
    public Supplier<MonthlyIncomeStatistics> supplier() {
        return null;
    }

    @Override
    public BiConsumer<MonthlyIncomeStatistics, Participant> accumulator() {
        return null;
    }

    @Override
    public BinaryOperator<MonthlyIncomeStatistics> combiner() {
        return null;
    }

    @Override
    public Function<MonthlyIncomeStatistics, MonthlyIncomeStatistics> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of();
    }
}
