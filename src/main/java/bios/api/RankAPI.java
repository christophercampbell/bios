package bios.api;

import bios.persist.*;
import com.google.inject.servlet.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 */
@Path("ranks")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RankAPI {
    private static final Logger LOG = LoggerFactory.getLogger(RankAPI.class);

    private RankManager rankManager;
    private TaxonManager taxonManager;

    @Inject
    public RankAPI(final RankManager rankManager, final TaxonManager taxonManager) {
        this.rankManager = rankManager;
        this.taxonManager = taxonManager;
    }

    @GET
    public Response get() {
        try {
            final List<bios.persist.Rank> ranks = rankManager.list();
            return Response.ok(ranks).build();
        } catch (PersistenceException e) {
            LOG.error("error retrieving ranks", e);
            return Response.serverError().build();
        }
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") final Integer id) {
        try {
            final Rank rank = rankManager.find(id);
            return Response.ok(rank).build();
        } catch (PersistenceException e) {
            LOG.error("error retrieving rank {}", id, e);
            return Response.serverError().build();
        }
    }

    @GET
    @Path("{id}/taxa")
    public Response getTaxa(@PathParam("id") final Integer id) {
        try {
            final Rank rank = rankManager.find(id);
            final List<Taxon> taxa = taxonManager.forRank(rank);
            return Response.ok(taxa).build();
        } catch (PersistenceException e) {
            LOG.error("error retrieving taxa for rank {}", id, e);
            return Response.serverError().build();
        }
    }
}
