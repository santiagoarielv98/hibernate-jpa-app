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
                .setParameter("nombre", "Santiago")
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

        System.out.println("========= consulta de campos personalizado del entity cliente =========");


        CriteriaQuery<Object[]> queryObject = criteriaBuilder.createQuery(Object[].class);

        from = queryObject.from(Cliente.class);

        queryObject.multiselect(from.get("id"), from.get("nombre"), from.get("apellido"));

        List<Object[]> registros = entityManager.createQuery(queryObject)
                .getResultList();

        registros.forEach(reg -> {
            Long id = (Long) reg[0];
            String nombre = (String) reg[1];
            String apellido = (String) reg[2];

            System.out.println(id + " - " + nombre + " - " + apellido);
        });

        System.out.println("========= consulta de campos personalizado del entity cliente con where =========");


        queryObject = criteriaBuilder.createQuery(Object[].class);

        from = queryObject.from(Cliente.class);

        queryObject.multiselect(from.get("id"), from.get("nombre"), from.get("apellido"))
                .where(criteriaBuilder.like(from.get("nombre"), "%lu%"));

        registros = entityManager.createQuery(queryObject)
                .getResultList();

        registros.forEach(reg -> {
            Long id = (Long) reg[0];
            String nombre = (String) reg[1];
            String apellido = (String) reg[2];

            System.out.println(id + "- " + nombre + "- " + apellido);
        });

        System.out.println("========= consulta de campos personalizado del entity cliente con where =========");

        queryObject = criteriaBuilder.createQuery(Object[].class);

        from = queryObject.from(Cliente.class);

        queryObject.multiselect(from.get("id"), from.get("nombre"), from.get("apellido"))
                .where(criteriaBuilder.equal(from.get("id"), 2L));

        Object[] registro = entityManager.createQuery(queryObject)
                .getSingleResult();

        Long id = (Long) registro[0];
        String nombre = (String) registro[1];
        String apellido = (String) registro[2];

        System.out.println(id + " - " + nombre + " - " + apellido);

        System.out.println("========= contar registros de la consulta con count =========");

        CriteriaQuery<Long> queryLong = criteriaBuilder.createQuery(Long.class);
        from = queryLong.from(Cliente.class);

        queryLong.select(criteriaBuilder.count(from.get("id")));
        Long count = entityManager.createQuery(queryLong)
                .getSingleResult();
        System.out.println(count);

        System.out.println("========= sumar datos de algún campo de la tabla =========");

        queryLong = criteriaBuilder.createQuery(Long.class);
        from = queryLong.from(Cliente.class);

        queryLong.select(criteriaBuilder.sum(from.get("id")));
        Long sum = entityManager.createQuery(queryLong)
                .getSingleResult();
        System.out.println(sum);

        System.out.println("========= consulta con el maximo id =========");

        queryLong = criteriaBuilder.createQuery(Long.class);
        from = queryLong.from(Cliente.class);

        queryLong.select(criteriaBuilder.max(from.get("id")));
        Long maxId = entityManager.createQuery(queryLong)
                .getSingleResult();
        System.out.println(maxId);

        System.out.println("========= consulta con el minimo id =========");

        queryLong = criteriaBuilder.createQuery(Long.class);
        from = queryLong.from(Cliente.class);

        queryLong.select(criteriaBuilder.min(from.get("id")));
        Long minId = entityManager.createQuery(queryLong)
                .getSingleResult();
        System.out.println(minId);

        System.out.println("========= ejemplo varios resultados de funciones de agregacion en una sola consulta =========");

        queryObject = criteriaBuilder.createQuery(Object[].class);
        from = queryObject.from(Cliente.class);

        queryObject.multiselect(
                criteriaBuilder.count(from.get("id")),
                criteriaBuilder.sum(from.get("id")),
                criteriaBuilder.max(from.get("id")),
                criteriaBuilder.min(from.get("id"))
        );

        registro = entityManager.createQuery(queryObject)
                .getSingleResult();

        Long countIds = (Long) registro[0];
        Long sumIds = (Long) registro[1];
        maxId = (Long) registro[2];
        minId = (Long) registro[3];

        System.out.println("count: " + countIds);
        System.out.println("sum: " + sumIds);
        System.out.println("max: " + maxId);
        System.out.println("min: " + minId);

        entityManager.close();

    }
}
