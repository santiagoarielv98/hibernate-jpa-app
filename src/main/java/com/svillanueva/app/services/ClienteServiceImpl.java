package com.svillanueva.app.services;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.repositories.ClienteRepository;
import com.svillanueva.app.repositories.CrudRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class ClienteServiceImpl implements ClienteService {

    private final EntityManager entityManager;

    private final CrudRepository<Cliente> clienteCrudRepository;

    public ClienteServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.clienteCrudRepository = new ClienteRepository(entityManager);
    }

    @Override
    public List<Cliente> listar() {
        return clienteCrudRepository.listar();
    }

    @Override
    public Optional<Cliente> porId(Long id) {
        return Optional.ofNullable(clienteCrudRepository.porId(id));
    }

    @Override
    public void guardar(Cliente cliente) {
        try {
            entityManager.getTransaction()
                    .begin();
            clienteCrudRepository.guardar(cliente);
            entityManager.getTransaction()
                    .commit();
        } catch (Exception e) {
            entityManager.getTransaction()
                    .rollback();
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            entityManager.getTransaction()
                    .begin();
            clienteCrudRepository.eliminar(id);
            entityManager.getTransaction()
                    .commit();
        } catch (Exception e) {
            entityManager.getTransaction()
                    .rollback();
        }
    }
}
