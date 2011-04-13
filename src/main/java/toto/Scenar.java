package toto;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 *
 * @author Matthieu Clochard
 */
public class Scenar {

    private Interclass interclass;

    @Inject
    public Scenar(Interclass interclass, Inittest test) {
        this.interclass = interclass;
    }

    @Produces
    private Inittest produce() {
        return new Inittest(interclass);
    }

}
