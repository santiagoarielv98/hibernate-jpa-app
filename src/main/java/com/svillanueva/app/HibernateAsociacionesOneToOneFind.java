package com.svillanueva.app;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.entity.ClienteDetalle;
import com.svillanueva.app.utils.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateAsociacionesOneToOneFind {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction()
                    .begin();

            Cliente cliente = em.find(Cliente.class, 1L);

            ClienteDetalle detalle = new ClienteDetalle(true, 5000L);
            em.persist(detalle);

            cliente.setDetalle(detalle);

            em.getTransaction()
                    .commit();
        } catch (Exception e) {
            em.getTransaction()
                    .rollback();

        } finally {
            em.close();
        }
    }
}
