package bios.api;

import bios.persist.PersistenceException;
import bios.persist.Taxon;
import bios.persist.TaxonManager;
import com.google.common.collect.Lists;
import com.google.inject.servlet.RequestScoped;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("tree")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TreeAPI {
    private static final Logger LOG = LoggerFactory.getLogger(TreeAPI.class);

    final TaxonManager taxonManager;

    @Inject
    public TreeAPI(final TaxonManager taxonManager) {
        this.taxonManager = taxonManager;
    }

    @GET
    @Path("{taxonId}")
    public Response getTree(@PathParam("taxonId") final Integer taxonId) {
        try {
            final Taxon taxon = taxonManager.find(taxonId);
            return Response.ok(makeGraph(taxon)).build();
        } catch (PersistenceException e) {
            LOG.error("error retrieving taxon {}", taxonId, e);
            return Response.serverError().build();
        }

    }

    private Node makeGraph(final Taxon root) throws PersistenceException {
        return makeNode(null, root);
    }

    private Node makeNode(final Taxon parent, final Taxon taxon) throws PersistenceException {
        Node node = new Node(taxon.getName());
        if (parent != null) {
            node.setParent(parent.getName());
        }

        List<Taxon> children = taxonManager.forParent(taxon);
        if (children != null) {
            for (Taxon child : children) {
                node.addChild(makeNode(taxon, child));
            }
        }
        return node;
    }

    public Response getTree() {
        Node root = new Node("root", null);

        Node A = new Node("A", root);
        Node B = new Node("B", root);

        root.addChild(A);
        root.addChild(B);

        A.addChild(new Node("a1", A));
        A.addChild(new Node("a2", A));
        A.addChild(new Node("a3", A));

        B.addChild(new Node("b1", B));

        return Response.ok(root).build();
    }

    static class Node {
        String name;
        String parent;
        List<Node> children;

        public Node(String name) {
            this(name, null);
        }

        public Node(String name, Node parent) {
            this(name, parent != null ? parent.getName() : null, new ArrayList<Node>());
        }

        public Node(String name, String parent, List<Node> children) {
            this.name = name;
            this.parent = parent;
            this.children = children;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public List<Node> getChildren() {
            return children;
        }

        public void setChildren(List<Node> children) {
            this.children = children;
        }

        @JsonIgnore
        public void addChild(Node node) {
            if (children == null)
                children = Lists.newArrayList();
            children.add(node);
        }
    }
}
