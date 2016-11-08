package bios.api;

import bios.persist.*;
import com.google.inject.servlet.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Path("taxon")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TaxonAPI {
    private static final Logger LOG = LoggerFactory.getLogger(TaxonAPI.class);

    private TaxonManager taxonManager;
    private RankManager rankManager;

    @Inject
    public TaxonAPI(final RankManager rankManager, final TaxonManager taxonManager) {
        this.rankManager = rankManager;
        this.taxonManager = taxonManager;
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") final Integer id) {
        try {
            final Taxon taxon = taxonManager.find(id);

            return Response.ok(taxon).build();
        } catch (PersistenceException e) {
            LOG.error("error retrieving taxon {}", id, e);
            return Response.serverError().build();
        }
    }

    @GET
    @Path("{id}/siblings")
    public Response getTaxa(@PathParam("id") final Integer id) {
        try {
            final Taxon taxon = taxonManager.find(id);
            final List<Taxon> taxa = taxonManager.forRank(taxon.getRank());
            return Response.ok(taxa).build();
        } catch (PersistenceException e) {
            LOG.error("error retrieving siblings for taxon {}", id, e);
            return Response.serverError().build();
        }
    }

    @GET
    @Path("{id}/chain")
    public Response getChain(@PathParam("id") final Integer id) {
        try {
            final List<Taxon> list = new ArrayList<Taxon>();
            Taxon taxon = taxonManager.find(id);
            while (taxon != null) {
                list.add(0, taxon);
                if (taxon.getParentId() != null)
                    taxon = taxonManager.find(taxon.getParentId());
                else
                    taxon = null; // stop walking back
            }
            return Response.ok(list.toArray()).build();
        } catch (PersistenceException e) {
            LOG.error("error retrieving siblings for taxon {}", id, e);
            return Response.serverError().build();
        }
    }

    @POST
    public Response put(@FormParam("name") final String name,
                        @FormParam("commonName") final String commonName,
                        @FormParam("rankId") final Integer rankId,
                        @FormParam("parentId") final Integer parentId) {
        try {
            final Rank rank = rankManager.find(rankId);
            final Taxon parent = taxonManager.find(parentId);

            LOG.info("name={}, commonName={}, rank={}, parent={}", name, commonName, rank.getName(), parent.getName());

            // TODO: make the insert

            return Response.noContent().build();
        } catch (PersistenceException e) {
            LOG.error("error creating taxon {}", name, e);
            return Response.serverError().build();
        }

    }
}

