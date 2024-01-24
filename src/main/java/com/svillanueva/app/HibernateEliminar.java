package com.svillanueva.app;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.utility.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.Scanner;

public class HibernateEliminar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el id del cliente a eliminar: ");
        Long id = scanner.nextLong();
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            entityManager.getTransaction()
                    .begin();

            Cliente cliente = entityManager.find(Cliente.class, id); // tiene que existir en el contexto de persistencia

            entityManager.remove(cliente);

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
