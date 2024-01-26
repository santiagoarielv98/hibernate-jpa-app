package com.svillanueva.app.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class JpaUtil {
    private static final EntityManagerFactory FACTORY;

    static {
        FACTORY = jakarta.persistence.Persistence.createEntityManagerFactory("relacionesJPA");
    }

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

    public static void close() {
        if (FACTORY != null) {
            FACTORY.close();
        }
    }

}
