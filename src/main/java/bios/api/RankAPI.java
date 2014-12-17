/*
 * Copyright (c) 2007-2014 Concurrent, Inc. All Rights Reserved.
 *
 * Project and contact information: http://www.concurrentinc.com/
 */

package bios.api;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bios.persist.PersistenceException;
import bios.persist.Rank;
import bios.persist.RankManager;
import bios.persist.Taxon;
import bios.persist.TaxonManager;
import com.google.inject.servlet.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
@Path("ranks")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RankAPI
  {
  private static final Logger LOG = LoggerFactory.getLogger( RankAPI.class );

  private RankManager rankManager;
  private TaxonManager taxonManager;

  @Inject
  public RankAPI( final RankManager rankManager, final TaxonManager taxonManager )
    {
    this.rankManager = rankManager;
    this.taxonManager = taxonManager;
    }

  @GET
  public Response get()
    {
    try
      {
      final List<bios.persist.Rank> ranks = rankManager.list();
      return Response.ok( ranks ).build();
      }
    catch( PersistenceException e )
      {
      LOG.error( "error retrieving ranks", e );
      return Response.serverError().build();
      }
    }

  @GET
  @Path("{id}")
  public Response get( @PathParam("id") final Integer id )
    {
    try
      {
      final Rank rank = rankManager.find( id );
      return Response.ok( rank ).build();
      }
    catch( PersistenceException e )
      {
      LOG.error( "error retrieving rank {}", id, e );
      return Response.serverError().build();
      }
    }

  @GET
  @Path("{id}/taxa")
  public Response getTaxa( @PathParam("id") final Integer id )
    {
    try
      {
      final Rank rank = rankManager.find( id );
      final List<Taxon> taxa = taxonManager.forRank( rank );
      return Response.ok( taxa ).build();
      }
    catch( PersistenceException e )
      {
      LOG.error( "error retrieving taxa for rank {}", id, e );
      return Response.serverError().build();
      }
    }
  }
