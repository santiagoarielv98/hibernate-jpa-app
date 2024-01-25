package com.svillanueva.app;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.utility.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;

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

        entityManager.close();
    }
}
