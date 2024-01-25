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

        System.out.println("========= consulta con order by asc desc =========");

        query = criteriaBuilder.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from)
                .orderBy(
                        criteriaBuilder.asc(from.get("nombre")), criteriaBuilder.desc(from.get("apellido"))
                );
        clientes = entityManager.createQuery(query)
                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("========= consulta por id =========");
        query = criteriaBuilder.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<Long> idParam = criteriaBuilder.parameter(Long.class, "id");


        query.select(from)
                .where(criteriaBuilder.equal(from.get("id"), idParam));


        Cliente cliente = entityManager.createQuery(query)
                .setParameter("id", 5L)
                .getSingleResult();
        System.out.println(cliente);


        System.out.println("========= consulta solo el nombre de los clientes =========");
        CriteriaQuery<String> stringCriteriaQuery = criteriaBuilder.createQuery(String.class);
        from = stringCriteriaQuery.from(Cliente.class);

        stringCriteriaQuery.select(from.get("nombre"));
        List<String> nombres = entityManager.createQuery(stringCriteriaQuery)
                .getResultList();

        nombres.forEach(System.out::println);

        System.out.println("========= consulta solo el nombre unicos de los clientes =========");
        stringCriteriaQuery = criteriaBuilder.createQuery(String.class);
        from = stringCriteriaQuery.from(Cliente.class);

        stringCriteriaQuery.select(from.get("nombre"))
                .distinct(true);

        nombres = entityManager.createQuery(stringCriteriaQuery)
                .getResultList();

        nombres.forEach(System.out::println);

        System.out.println("========= consulta por nombres y apellidos concatenados =========");

        stringCriteriaQuery = criteriaBuilder.createQuery(String.class);
        from = stringCriteriaQuery.from(Cliente.class);

        stringCriteriaQuery.select(criteriaBuilder.concat(criteriaBuilder.concat(from.get("nombre"), " "), from.get("apellido")));
        nombres = entityManager.createQuery(stringCriteriaQuery)
                .getResultList();
        nombres.forEach(System.out::println);

        System.out.println("========= consulta por nombres y apellidos concatenados upper =========");

        stringCriteriaQuery = criteriaBuilder.createQuery(String.class);
        from = stringCriteriaQuery.from(Cliente.class);

        stringCriteriaQuery.select(criteriaBuilder.upper(criteriaBuilder.concat(criteriaBuilder.concat(from.get("nombre"), " "), from.get("apellido"))));
        nombres = entityManager.createQuery(stringCriteriaQuery)
                .getResultList();
        nombres.forEach(System.out::println);


        entityManager.close();
    }
}
