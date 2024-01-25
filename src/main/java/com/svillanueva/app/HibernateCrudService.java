package com.svillanueva.app;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.services.ClienteService;
import com.svillanueva.app.services.ClienteServiceImpl;
import com.svillanueva.app.utility.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class HibernateCrudService {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        ClienteService clienteService = new ClienteServiceImpl(entityManager);

        System.out.println("======== listar ========");
        List<Cliente> listar = clienteService.listar();
        listar.forEach(System.out::println);

        System.out.println("======== obtener por id ========");
        Optional<Cliente> cliente = clienteService.porId(2L);
        cliente.ifPresent(System.out::println);

        System.out.println("======== insertar nuevo cliente ========");
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre("Cristian");
        nuevoCliente.setApellido("Castro");
        nuevoCliente.setFormaPago("paypal");
        clienteService.guardar(nuevoCliente);
        System.out.println("Cliente guardado con exito");

        System.out.println("======== listar ========");
        listar.forEach(System.out::println);

        System.out.println("======== editar usuario ========");
        Long id = nuevoCliente.getId();
        cliente = clienteService.porId(id);

        cliente.ifPresent(c -> {
            c.setFormaPago("mercado pago");
            clienteService.guardar(c);
            System.out.println("Cliente editado con exito!");
        });

        System.out.println("======== eliminar cliente ========");
        id = nuevoCliente.getId();
        cliente = clienteService.porId(id);
        cliente.ifPresent(c -> clienteService.eliminar(c.getId()));

        entityManager.close();
    }
}
