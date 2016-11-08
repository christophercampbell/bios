package bios.persist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.inject.Inject;

/**
 *
 */
public class PersistenceFactory {
    final SessionFactory sessionFactory;
    final ServiceRegistry serviceRegistry;

    @Inject
    public PersistenceFactory() {
        serviceRegistry = new ServiceRegistryBuilder().buildServiceRegistry();
        sessionFactory = new Configuration().configure().buildSessionFactory(serviceRegistry);
    }

    public Session openSession() {
        return sessionFactory.openSession();
    }

    public void close(Session session) {
        if (session != null)
            session.close();
    }

}
