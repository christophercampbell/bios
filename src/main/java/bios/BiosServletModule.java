package bios;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import bios.filter.StaticContent;
import bios.json.ObjectMapperProvider;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class BiosServletModule extends JerseyServletModule
  {
  static final Logger LOG = LoggerFactory.getLogger( BiosServletModule.class );

  static final String DEFAULT_PROPERTIES = "/bios.properties";
  static final String PROPERTIES_KEY = "bios.properties";

  @Override
  protected void configureServlets()
    {
    // Bind properties
    Properties properties = new Properties();
    try
      {
      InputStream stream;
      if( System.getProperties().containsKey( PROPERTIES_KEY ) )
        {
        final String path = System.getProperty( PROPERTIES_KEY );
        final File file = new File( path );
        LOG.info( "loading {} from filesystem", file.getAbsolutePath() );
        stream = FileUtils.openInputStream( file );
        }
      else
        {
        LOG.info( "loading {} from classpath", DEFAULT_PROPERTIES );
        stream = this.getClass().getResourceAsStream( DEFAULT_PROPERTIES );
        }
      properties.load( stream );
      Names.bindProperties( binder(), properties );
      stream.close();
      }
    catch( IOException e )
      {
      throw new RuntimeException( e );
      }

    // JSON mapper
    bind( JacksonJsonProvider.class ).in( Singleton.class );
    bind( ObjectMapperProvider.class ).in( Singleton.class );

    // Static content
    bind( StaticContent.class ).in( Singleton.class );
    for( String type : StaticContent.types() )
      {
      filter( "*." + type ).through( StaticContent.class );
      }

    // Rest endpoints for everything else
    serve( "/*" ).with( GuiceContainer.class,
      new ImmutableMap.Builder<String, String>()
        .put( PackagesResourceConfig.PROPERTY_PACKAGES, "bios.api" )
        .put( JSONConfiguration.FEATURE_POJO_MAPPING, "true" )
        .build() );
    }

  }
