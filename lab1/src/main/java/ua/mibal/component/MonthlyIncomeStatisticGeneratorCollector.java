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
 * A {@link Collector} implementation for generating monthly income statistics from {@link Participant} objects.
 * This collector accumulates {@link Participant} instances into a {@link MonthlyIncomeStatistics} object,
 * which provides statistical information about the participants' monthly incomes.
 *
 * <p>This collector can be used in Java streams to efficiently gather income statistics
 * from a collection of {@link Participant} objects.</p>
 *
 * <p>Usage example:</p>
 * <pre>
 * List<Participant> participants = // ... list of participants
 * MonthlyIncomeStatistics statistics = participants.stream()
 *     .collect(new MonthlyIncomeStatisticGeneratorCollector());
 * </pre>
 *
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class MonthlyIncomeStatisticGeneratorCollector implements Collector<Participant, MonthlyIncomeStatistics, MonthlyIncomeStatistics> {

    @Override
    public Supplier<MonthlyIncomeStatistics> supplier() {
        return MonthlyIncomeStatistics::new;
    }

    @Override
    public BiConsumer<MonthlyIncomeStatistics, Participant> accumulator() {
        return (stats, participant) -> {
            int income = participant.monthlyIncome();
            stats.record(income);
        };
    }

    @Override
    public BinaryOperator<MonthlyIncomeStatistics> combiner() {
        return (stats1, stats2) -> {
            stats1.record(stats2);
            return stats1;
        };
    }

    @Override
    public Function<MonthlyIncomeStatistics, MonthlyIncomeStatistics> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of();
    }
}
