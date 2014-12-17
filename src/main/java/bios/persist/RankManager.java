/*
 * Copyright (c) 2007-2013 Concurrent, Inc. All Rights Reserved.
 *
 * Project and contact information: http://www.concurrentinc.com/
 */

package bios.persist;

import javax.inject.Inject;

/** Requires hibernate.cfg.xml and hibernate.properties on the classpath. */
public class RankManager extends PersistenceManager<Rank>
  {

  @Inject
  public RankManager( final PersistenceFactory factory )
    {
    super( factory );
    }
  }
