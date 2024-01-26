package com.svillanueva.app;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.utility.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HibernateCriteriaBusquedaDinamica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Filtro para nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("Filtro para el apellido: ");
        String apellido = scanner.nextLine();

        System.out.println("Filtro para el forma de pago: ");
        String formaPago = scanner.nextLine();

        EntityManager em = JpaUtil.getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
        Root<Cliente> from = query.from(Cliente.class);

        List<Predicate> predicates = new ArrayList<>();

        if (nombre != null && !nombre.isEmpty()) {
            predicates.add(cb.like(from.get("nombre"), "%" + nombre + "%"));
        }

        if (apellido != null && !apellido.isEmpty()) {
            predicates.add(cb.like(from.get("apellido"), "%" + apellido + "%"));
        }

        if (formaPago != null && !formaPago.isEmpty()) {
            predicates.add(cb.like(from.get("formaPago"), "%" + formaPago + "%"));
        }

        query.select(from)
                .where(predicates.toArray(new Predicate[0]));

        List<Cliente> clientes = em.createQuery(query)
                .getResultList();
        clientes.forEach(System.out::println);

        em.close();
    }
}
