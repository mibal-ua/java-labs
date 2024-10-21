package ua.mibal.component;

import ua.mibal.model.Participant;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

/**
 * A custom {@link Gatherer} implementation for filtering and collecting {@link Participant} objects based on city criteria.
 * <p>
 * This gatherer allows for skipping a specified number of participants from a particular city
 * and collecting a limited number of participants overall.
 *
 * <p>Example usage:</p>
 * <pre>
 * Stream<Participant> participants = // ... stream of participants
 * List<Participant> result = participants.gather(new CityGatherer("Kyiv", 5, 100))
 *                                       .toList();
 * </pre>
 *
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class CityGatherer implements Gatherer<Participant, List<Participant>, Participant> {
    private final String cityToSkip;
    private final int skipCount;
    private final int limit;

    /**
     * Constructs a new CityGatherer with specified parameters.
     *
     * @param cityToSkip The name of the city for which participants should be initially skipped.
     * @param skipCount The number of participants from the specified city to skip.
     * @param limit The maximum number of participants to collect.
     */
    public CityGatherer(String cityToSkip, int skipCount, int limit) {
        this.cityToSkip = cityToSkip;
        this.skipCount = skipCount;
        this.limit = limit;
    }

    /**
     * Provides the integration logic for the gatherer.
     *
     * @return An {@link Integrator} that implements the filtering and collecting logic.
     */
    @Override
    public Integrator<List<Participant>, Participant, Participant> integrator() {
        return new Integrator<>() {
            private int skipped = 0;

            @Override
            public boolean integrate(List<Participant> state, Participant element, Downstream<? super Participant> downstream) {
                if (state.size() >= limit) {
                    return false;
                }

                if (element.city().equals(cityToSkip) && skipped < skipCount) {
                    skipped++;
                    return true;
                }

                state.add(element);
                return true;
            }
        };
    }

    /**
     * Provides the initial state for the gatherer.
     *
     * @return A {@link Supplier} that creates a new {@link ArrayList} to store participants.
     */
    @Override
    public Supplier<List<Participant>> initializer() {
        return ArrayList::new;
    }

    /**
     * Defines the finishing operation for the gatherer.
     *
     * @return A {@link BiConsumer} that pushes collected participants to the downstream.
     */
    @Override
    public BiConsumer<List<Participant>, Downstream<? super Participant>> finisher() {
        return (state, downstream) -> {
            for (Participant participant : state) {
                downstream.push(participant);
            }
        };
    }
}
