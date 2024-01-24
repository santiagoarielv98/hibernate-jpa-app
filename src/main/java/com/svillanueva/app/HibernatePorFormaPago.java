package com.svillanueva.app;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.utility.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.Scanner;

public class HibernatePorFormaPago {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManager entityManager = JpaUtil.getEntityManager();
        Query query = entityManager.createQuery("SELECT c from Cliente c WHERE c.formaPago = ?1", Cliente.class);

        System.out.println("Ingrese forma de pago: ");
        String id = scanner.next();

        query.setParameter(1, id);
        query.setMaxResults(1);

        Cliente c = (Cliente) query.getSingleResult();

        System.out.println(c);

        entityManager.close();
    }
}
