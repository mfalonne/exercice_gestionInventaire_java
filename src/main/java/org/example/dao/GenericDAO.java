package org.example.dao;

import org.example.util.SessionFactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class GenericDAO<T> {

    private final SessionFactory sessionFactory;
    private final Class<T> type;

    public GenericDAO(Class<T> type) {
        this.sessionFactory = SessionFactorySingleton.getSessionFactory();
        this.type = type;
    }

    public T createOrUpdate(T entity) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(entity);
            tx.commit();
            return entity;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("[Erreur CREATE/UPDATE] " + e.getMessage());
            return null;
        }
    }

    public T get(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(type, id);
        } catch (Exception e) {
            System.err.println("[Erreur GET] " + e.getMessage());
            return null;
        }
    }

    public boolean delete(int id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            T entity = session.get(type, id);
            if (entity == null) return false;

            tx = session.beginTransaction();
            session.delete(entity);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("[Erreur DELETE] " + e.getMessage());
            return false;
        }
    }

    public List<T> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM " + type.getSimpleName(), type).list();
        } catch (Exception e) {
            System.err.println("[Erreur GET ALL] " + e.getMessage());
            return null;
        }
    }
}
