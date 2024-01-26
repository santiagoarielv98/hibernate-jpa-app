package com.svillanueva.app;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.utils.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateFetchLazyOneToManyJoinFetch {

    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        Cliente cliente = em.createQuery("select c from Cliente  c left join fetch c.direcciones where  c.id = :id",Cliente.class)
                .setParameter("id",1L)
                .getSingleResult();


        System.out.println("Cliente: " + cliente);
        em.close();
    }
}
