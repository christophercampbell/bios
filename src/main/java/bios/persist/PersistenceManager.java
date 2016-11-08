package bios.persist;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Base persistence manager class. Extend this for class for basic domain object persistence management.
 */
@SuppressWarnings("unchecked")
public class PersistenceManager<T> {
    final private PersistenceFactory factory;
    final private Class typeOfT;

    public PersistenceManager(final PersistenceFactory factory) {
        this.factory = factory;
        this.typeOfT = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void save(T object) throws PersistenceException {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(object);
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            throw new PersistenceException(e);
        } finally {
            factory.close(session);
        }
    }

    public T find(Integer id) throws PersistenceException {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            T object = (T) session.get(typeOfT, id);
            tx.commit();
            return object;
        } catch (HibernateException e) {
            tx.rollback();
            throw new PersistenceException(e);
        } finally {
            factory.close(session);
        }
    }

    public List<T> list() throws PersistenceException {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            // List all of them
            Criteria criteria = session.createCriteria(typeOfT);
            return criteria.list();
        } catch (HibernateException e) {
            tx.rollback();
            throw new PersistenceException(e);
        } finally {
            factory.close(session);
        }
    }

    public T findFirst(String field, Object val) throws PersistenceException {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(typeOfT);
            criteria.add(Restrictions.eq(field, val));
            criteria.setFirstResult(0);
            List<T> result = criteria.list();
            return (result != null && result.size() > 0) ? result.get(0) : null; // T should extend Nullable and return NULL object version
        } catch (HibernateException e) {
            tx.rollback();
            throw new PersistenceException(e);
        } finally {
            factory.close(session);
        }

    }

    public List<T> find(String field, Object val) throws PersistenceException {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(typeOfT);
            criteria.add(Restrictions.eq(field, val));
            return criteria.list();
        } catch (HibernateException e) {
            tx.rollback();
            throw new PersistenceException(e);
        } finally {
            factory.close(session);
        }
    }
}
