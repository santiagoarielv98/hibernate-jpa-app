package com.svillanueva.app;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.utility.JpaUtil;
import jakarta.persistence.EntityManager;

import javax.swing.*;

public class HibernateCrear {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {

            entityManager.getTransaction()
                    .begin();

            String nombre = JOptionPane.showInputDialog("Ingrese nombre");
            String apellido = JOptionPane.showInputDialog("Ingrese apellido");
            String formaPago = JOptionPane.showInputDialog("Ingrese forma de pago");

            Cliente c = new Cliente();

            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setFormaPago(formaPago);

            entityManager.persist(c);
            entityManager.getTransaction()
                    .commit();
            System.out.println("id del cliente registrado es: "+ c.getId());
            c = entityManager.find(Cliente.class, c.getId());
            System.out.println(c);
        } catch (Exception e) {
            entityManager.getTransaction()
                    .rollback();
        } finally {
            entityManager.close();
        }
    }
}
