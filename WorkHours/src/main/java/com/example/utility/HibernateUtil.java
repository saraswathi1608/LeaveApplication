package com.example.utility;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
        	
        	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
        			configure().build();
        			SessionFactory sessionFactory= new Configuration().buildSessionFactory(serviceRegistry); 
            // Create the SessionFactory from hibernate.cfg.xml
        			return sessionFactory;
          //  return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            ex.printStackTrace(); // Print the stack trace
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
