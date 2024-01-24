package com.svillanueva.app;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.utility.JpaUtil;
import jakarta.persistence.EntityManager;

import javax.swing.*;

public class HibernateEditar {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            entityManager.getTransaction()
                    .begin();

            Long id = Long.valueOf(JOptionPane.showInputDialog("Ingrese el Id del cliente que quiere modificar: "));
            Cliente c = entityManager.find(Cliente.class, id);

            String nombre = JOptionPane.showInputDialog("Ingrese nuevo nombre", c.getNombre());
            String apellido = JOptionPane.showInputDialog("Ingrese nuevo apellido", c.getApellido());
            String formaPago = JOptionPane.showInputDialog("Ingrese forma pago", c.getFormaPago());

            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setFormaPago(formaPago);

            entityManager.merge(c);

            System.out.println(c);
            entityManager.getTransaction()
                    .commit();
        } catch (Exception e) {
            entityManager.getTransaction()
                    .rollback();
        } finally {
            entityManager.close();
        }
    }
}
