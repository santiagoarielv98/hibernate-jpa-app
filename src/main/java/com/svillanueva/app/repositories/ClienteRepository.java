package com.svillanueva.app.repositories;

import com.svillanueva.app.entity.Cliente;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ClienteRepository implements CrudRepository<Cliente> {
    private final EntityManager entityManager;

    public ClienteRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Cliente> listar() {
        return entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class)
                .getResultList();
    }

    @Override
    public Cliente porId(Long id) {
        return entityManager.find(Cliente.class, id);
    }

    @Override
    public void guardar(Cliente cliente) {
        if (cliente.getId() != null && cliente.getId() > 0) {
            entityManager.merge(cliente);
        } else {
            entityManager.persist(cliente);
        }
    }

    @Override
    public void eliminar(Long id) {
        Cliente cliente = this.porId(id);
        entityManager.remove(cliente);
    }
}
