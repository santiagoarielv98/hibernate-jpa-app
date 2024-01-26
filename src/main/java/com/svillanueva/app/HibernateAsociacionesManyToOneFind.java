package com.svillanueva.app;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.entity.Factura;
import com.svillanueva.app.utils.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateAsociacionesManyToOneFind {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction()
                    .begin();

            Cliente cliente = em.find(Cliente.class, 1L);

            Factura factura = new Factura("Factura 1", 1000L);

            factura.setCliente(cliente);
            em.persist(factura);

            System.out.println(factura.getCliente());

            em.getTransaction()
                    .commit();

            em.getTransaction()
                    .begin();

            em.getTransaction()
                    .commit();
        } catch (Exception e) {
            em.getTransaction()
                    .rollback();
//            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
