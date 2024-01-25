package com.svillanueva.app;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.utility.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;

import java.util.Arrays;
import java.util.List;

public class HibernateCriteria {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Cliente> query = criteriaBuilder.createQuery(Cliente.class);
        Root<Cliente> from = query.from(Cliente.class);

        query.select(from);
        List<Cliente> clientes = entityManager.createQuery(query)
                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("========= listar where equals =========");
        query = criteriaBuilder.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<String> nombreParams = criteriaBuilder.parameter(String.class, "nombre");

        query.select(from)
                .where(criteriaBuilder.equal(from.get("nombre"), nombreParams));

        clientes = entityManager.createQuery(query)
//                .setParameter("nombre", "Santiago")
                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("========= usando where like para buscar clientes por nombre =========");
        query = criteriaBuilder.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<String> nombreParamLike = criteriaBuilder.parameter(String.class, "nombreParam");

//        query.where(criteriaBuilder.like(from.get("nombre"), "%tia%"));
        query.select(from)
//                .where(criteriaBuilder.like(from.get("nombre"), nombreParamLike));
                .where(criteriaBuilder.like(criteriaBuilder.upper(from.get("nombre")), criteriaBuilder.upper(nombreParamLike)));

        clientes = entityManager.createQuery(query)
                .setParameter("nombreParam", "%ago%")
                .getResultList();
        clientes.forEach(System.out::println);


        System.out.println("========= ejemplo usando where between para rangos =========");

        query = criteriaBuilder.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from)
                .where(criteriaBuilder.between(from.get("id"), 2L, 6L));
        clientes = entityManager.createQuery(query)
                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("========= ejemplo usando where in =========");
        query = criteriaBuilder.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        ParameterExpression<List> listParams = criteriaBuilder.parameter(List.class, "nombres");

        query.select(from)
                .where(from.get("nombre")
                        .in(
                                // "Santiago", "John", "Lou"
                                listParams
                        ));


        clientes = entityManager.createQuery(query)
                .setParameter("nombres", Arrays.asList("Santiago", "John", "Lou"))
                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("========= filtrar usando predicados mayor que o mayor igual que =========");
        query = criteriaBuilder.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from)
                .where(criteriaBuilder.ge(from.get("id"), 3L));

        clientes = entityManager.createQuery(query)
                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("========= filtrar 2 usando predicados mayor que o mayor igual que =========");
        query = criteriaBuilder.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from)
                .where(criteriaBuilder.gt(criteriaBuilder.length(from.get("nombre")), 3L));

        clientes = entityManager.createQuery(query)
                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("========= consulta con los predicados conjuncion and y disyunción or =========");

        query = criteriaBuilder.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        Predicate porNombre = criteriaBuilder.equal(from.get("nombre"), "Santiago");
        Predicate porFormaPago = criteriaBuilder.equal(from.get("formaPago"), "debito");

        query.select(from)
                .where(criteriaBuilder.and(porNombre, porFormaPago));

        clientes = entityManager.createQuery(query)
                .getResultList();
        clientes.forEach(System.out::println);

        entityManager.close();
    }
}
