package com.svillanueva.app;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.entity.Factura;
import com.svillanueva.app.utils.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateAsociacionesOneToManyBidireccionalFind {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction()
                    .begin();

            Cliente cliente = em.find(Cliente.class, 1L);

            Factura f3 = new Factura("compras 4", 5000L);
            Factura f4 = new Factura("compras 5", 7000L);

            cliente.addFactura(f3);
            cliente.addFactura(f4);

//            em.merge(cliente);

            System.out.println(cliente);
            em.getTransaction()
                    .commit();

            em.getTransaction()
                    .begin();

//            Factura factura3 = em.find(Factura.class, 1L);
            Factura factura3 = new Factura("compras 4", 5000L);

            f3.setId(1L);

            cliente.removeFactura(factura3);

            em.getTransaction()
                    .commit();

            System.out.println(cliente);
        } catch (Exception e) {
            em.getTransaction()
                    .rollback();

        } finally {
            em.close();
        }
    }
}
