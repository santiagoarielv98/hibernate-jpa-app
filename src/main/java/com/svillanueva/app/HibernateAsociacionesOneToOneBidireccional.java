package com.svillanueva.app;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.entity.ClienteDetalle;
import com.svillanueva.app.utils.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateAsociacionesOneToOneBidireccional {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction()
                    .begin();

            Cliente cliente = new Cliente("Cata", "Edu");
            cliente.setFormaPago("paypal");

            ClienteDetalle detalle = new ClienteDetalle(true, 5000L);

            cliente.addDetalle(detalle);

            em.persist(cliente);

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
