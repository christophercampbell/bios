/*
 * Copyright (c) 2007-2013 Concurrent, Inc. All Rights Reserved.
 *
 * Project and contact information: http://www.concurrentinc.com/
 */

package bios.persist;

import java.util.Properties;

import com.google.inject.Binder;
import com.google.inject.Module;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class PersistenceModule implements Module
  {
  static final Logger LOG = LoggerFactory.getLogger( PersistenceModule.class );

  @Override
  public void configure( Binder binder )
    {
    binder.bind( PersistenceFactory.class ).asEagerSingleton();
    binder.bind( RankManager.class ).asEagerSingleton();
    binder.bind( TaxonManager.class ).asEagerSingleton();

    // Override hibernate properties if present
    final Configuration configuration = new Configuration();
    final Properties properties = configuration.getProperties();
    final Properties system = System.getProperties();
    for( final Object key : system.keySet() )
      {
      if( key instanceof String && ( (String) key ).startsWith( "hibernate." ) )
        {
        final String prop = (String) key;
        final String value = system.getProperty( prop );
        final String displayValue = prop.contains( "password" ) ? "******" : value;
        LOG.info( "OVERRIDE {}={}", prop, displayValue );
        properties.setProperty( prop, value );
        }
      }
    configuration.setProperties( properties );

    }
  }
