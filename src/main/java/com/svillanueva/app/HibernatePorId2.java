package com.svillanueva.app;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.utility.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.Scanner;

public class HibernatePorId2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManager entityManager = JpaUtil.getEntityManager();
        System.out.println("Ingrese ID: ");
        long id = scanner.nextLong();
        Cliente c = entityManager.find(Cliente.class, id);

        System.out.println(c);

        Cliente c2 = entityManager.find(Cliente.class, id); // guarda en cache
        System.out.println(c2);

        entityManager.close();
    }
}
