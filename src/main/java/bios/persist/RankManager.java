package bios.persist;

import javax.inject.Inject;

/**
 * Requires hibernate.cfg.xml and hibernate.properties on the classpath.
 */
public class RankManager extends PersistenceManager<Rank> {

    @Inject
    public RankManager(final PersistenceFactory factory) {
        super(factory);
    }
}
