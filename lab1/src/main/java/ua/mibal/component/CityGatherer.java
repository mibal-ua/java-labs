package ua.mibal.component;

import ua.mibal.model.Participant;

import java.util.stream.Gatherer;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class CityGatherer implements Gatherer<Participant, Participant, Participant> {

    //todo
    public CityGatherer(String cityToSkip, int skipCount, int limit) {
    }

    @Override
    public Integrator<Participant, Participant, Participant> integrator() {
        return null;
    }
}
