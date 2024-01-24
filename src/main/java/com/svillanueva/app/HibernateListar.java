package com.svillanueva.app;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.utility.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateListar {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class)
                .getResultList()
                .forEach(System.out::println);

        entityManager.close();
    }
}