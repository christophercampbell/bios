/*
 * Copyright (c) 2007-2013 Concurrent, Inc. All Rights Reserved.
 *
 * Project and contact information: http://www.concurrentinc.com/
 */

package bios.persist;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 */
public class PersistenceFactory
  {
  final SessionFactory sessionFactory;
  final ServiceRegistry serviceRegistry;

  @Inject
  public PersistenceFactory()
    {
    serviceRegistry = new ServiceRegistryBuilder().buildServiceRegistry();
    sessionFactory = new Configuration().configure().buildSessionFactory( serviceRegistry );
    }

  public Session openSession()
    {
    return sessionFactory.openSession();
    }

  public void close( Session session )
    {
    if( session != null )
      session.close();
    }

  }
