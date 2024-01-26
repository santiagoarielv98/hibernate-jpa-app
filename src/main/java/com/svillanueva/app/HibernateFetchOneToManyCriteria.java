package com.svillanueva.app;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class HibernateFetchOneToManyCriteria {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
        Root<Cliente> cliente = query.from(Cliente.class);

        cliente.fetch("facturas", JoinType.LEFT);
        cliente.fetch("detalle", JoinType.LEFT);
        query.select(cliente)
                .distinct(true);

        List<Cliente> clientes = em.createQuery(query)
                .getResultList();


        em.close();
    }
}
