package com.svillanueva.app;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.entity.Factura;
import com.svillanueva.app.utils.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateAsociacionesOneToManyBidireccional {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction()
                    .begin();

            Cliente cliente = new Cliente("Santi", "Villanueva");
            cliente.setFormaPago("paypal");

            Factura f1 = new Factura("compras", 5000L);
            Factura f2 = new Factura("compras 2", 7000L);

            cliente.addFactura(f1);
            cliente.addFactura(f2);

            em.persist(cliente);

            System.out.println(cliente);
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
