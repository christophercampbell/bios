/*
 * Copyright (c) 2007-2014 Concurrent, Inc. All Rights Reserved.
 *
 * Project and contact information: http://www.concurrentinc.com/
 */

package bios.persist;

import java.util.List;
import javax.inject.Inject;

/**
 *
 */
public class TaxonManager extends PersistenceManager<Taxon>
  {

  @Inject
  public TaxonManager( PersistenceFactory factory )
    {
    super( factory );
    }

  public List<Taxon> forRank( final Rank rank ) throws PersistenceException
    {
    return find( "rank", rank );
    }

  public List<Taxon> forParent( final Taxon parent ) throws PersistenceException
    {
    return find( "parentId", parent.getId() );
    }

  }
