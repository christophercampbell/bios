package bios.persist;

import javax.inject.Inject;
import java.util.List;

/**
 *
 */
public class TaxonManager extends PersistenceManager<Taxon> {

    @Inject
    public TaxonManager(PersistenceFactory factory) {
        super(factory);
    }

    public List<Taxon> forRank(final Rank rank) throws PersistenceException {
        return find("rank", rank);
    }

    public List<Taxon> forParent(final Taxon parent) throws PersistenceException {
        return find("parentId", parent.getId());
    }

}
