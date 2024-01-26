package com.svillanueva.app;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.utils.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateFetchLazyOneToMany {

    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        Cliente cliente = em.find(Cliente.class, 1L);

        System.out.println("Cliente: " + cliente);
        em.close();
    }
}
