package com.svillanueva.app;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.entity.Direccion;
import com.svillanueva.app.utils.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.Arrays;
import java.util.List;

public class HibernateAsociacionesOneToMany {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction()
                    .begin();

            Cliente cliente = new Cliente("Ariel", "Villanueva");
            cliente.setFormaPago("mercado pago");

            List<Direccion> direcciones = Arrays.asList(
                    new Direccion("calle falsa", 123),
                    new Direccion("calle falsa", 856)
            );

            cliente.setDirecciones(direcciones);

            em.persist(cliente);

            System.out.println(cliente);

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
