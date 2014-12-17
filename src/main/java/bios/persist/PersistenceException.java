/*
 * Copyright (c) 2007-2013 Concurrent, Inc. All Rights Reserved.
 *
 * Project and contact information: http://www.concurrentinc.com/
 */

package bios.persist;

/**
 *
 */
public class PersistenceException extends Exception
  {
  public PersistenceException()
    {
    }

  public PersistenceException( String string )
    {
    super( string );
    }

  public PersistenceException( String string, Throwable throwable )
    {
    super( string, throwable );
    }

  public PersistenceException( Throwable throwable )
    {
    super( throwable );
    }
  }
