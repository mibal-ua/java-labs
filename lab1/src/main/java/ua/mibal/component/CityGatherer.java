package ua.mibal.component;

import ua.mibal.model.Participant;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class CityGatherer implements Gatherer<Participant, List<Participant>, Participant> {
    private final String cityToSkip;
    private final int skipCount;
    private final int limit;

    public CityGatherer(String cityToSkip, int skipCount, int limit) {
        this.cityToSkip = cityToSkip;
        this.skipCount = skipCount;
        this.limit = limit;
    }

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

    @Override
    public Supplier<List<Participant>> initializer() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<Participant>, Downstream<? super Participant>> finisher() {
        return (state, downstream) -> {
            for (Participant participant : state) {
                downstream.push(participant);
            }
        };
    }
}
