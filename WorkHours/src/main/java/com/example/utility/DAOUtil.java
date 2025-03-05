package com.example.utility;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class DAOUtil {
    
	public <T> List<T> executeQuery(String hql, Map<String, Object> parameters, Class<T> resultClass) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<T> query = session.createQuery(hql, resultClass);
            parameters.forEach(query::setParameter);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while executing query:" + e);
            return null;
        }
    }
	
	public <T> T executeSingleResultQuery(String hql, Map<String, Object> parameters, Class<T> resultClass) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        Query<T> query = session.createQuery(hql, resultClass);
	        parameters.forEach(query::setParameter);
	        return query.getSingleResult();
	    } catch (NoResultException e) {
	        System.out.println("No result found for the query: " + e);
	        return null;
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error while executing query: " + e);
	        return null;
	    }
	}

    
    public static <T> void saveEntity(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Error while saving entity: " + e);
        }
    }

    public <T> void updateEntity(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Error while updating entity: " + e);
        }
    }
    
    public <T> void saveBulkEntities(List<T> entities) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            int batchSize = 50; // Adjust this to control batch size
            for (int i = 0; i < entities.size(); i++) {
                session.save(entities.get(i));
                if (i % batchSize == 0 && i > 0) {
                    session.flush();
                    session.clear();
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Error while saving entities: " + e);
        }
    }
    
    public <T> void deleteEntity(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("Error while deleting entity: " + e);
        }
    }

}
