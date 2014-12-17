/*
 * Copyright (c) 2007-2014 Concurrent, Inc. All Rights Reserved.
 *
 * Project and contact information: http://www.concurrentinc.com/
 */

package bios.json;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

@Provider
public class ObjectMapperProvider implements ContextResolver<ObjectMapper>
  {
  private final ObjectMapper mapper;

  public ObjectMapperProvider()
    {
    mapper = new ObjectMapper();
    mapper.enable( Feature.INDENT_OUTPUT );
    mapper.enable( Feature.WRITE_EMPTY_JSON_ARRAYS );
    mapper.enable( Feature.WRITE_NULL_MAP_VALUES );
    }

  @Override
  public ObjectMapper getContext( Class<?> type )
    {
    return mapper;
    }
  }
