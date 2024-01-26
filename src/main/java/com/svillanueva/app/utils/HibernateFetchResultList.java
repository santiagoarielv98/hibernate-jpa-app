package com.svillanueva.app.utils;

import com.svillanueva.app.entity.Cliente;
import jakarta.persistence.EntityManager;

import java.util.List;

public class HibernateFetchResultList {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        List<Cliente> clientes = em.createQuery("select distinct c from Cliente c left outer join fetch c.direcciones", Cliente.class)
                .getResultList();

        clientes.forEach(cliente -> System.out.println(cliente.getNombre() + " direcciones: " + cliente.getDirecciones()));


        em.close();
    }
}
