package com.svillanueva.app;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.utility.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Scanner;

public class HibernateResultListWhere {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManager entityManager = JpaUtil.getEntityManager();
        TypedQuery<Cliente> query = entityManager.createQuery("SELECT c from Cliente c WHERE c.formaPago = ?1", Cliente.class);

        System.out.println("Ingrese forma de pago: ");
        String pago = scanner.next();

        query.setParameter(1, pago);

        List<Cliente> clientes = query.getResultList();

        clientes.forEach(System.out::println);

        entityManager.close();
    }
}
