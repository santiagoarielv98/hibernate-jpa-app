package com.svillanueva.app;

import com.svillanueva.app.entity.Cliente;
import com.svillanueva.app.utility.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class HibernateQL {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        System.out.println("========== consultar todo ==========");
        List<Cliente> clientes = entityManager.createQuery("SELECT c from Cliente c", Cliente.class)
                .getResultList();
        clientes.forEach(System.out::println);

        System.out.println("========== consulta por id ==========");
        Cliente cliente = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.id=:idCliente", Cliente.class)
                .setParameter("idCliente", 1L)
                .getSingleResult();
        System.out.println(cliente);

        System.out.println("========== consulta solo el nombre por el id ==========");
        String nombreCliente = entityManager.createQuery("SELECT c.nombre FROM Cliente c WHERE c.id=:id", String.class)
                .setParameter("id", 1L)
                .getSingleResult();
        System.out.println(nombreCliente);

        System.out.println("========== consulta por campos personalizados ==========");
        Object[] clienteCamposPersonalizado = entityManager.createQuery("SELECT c.id, c.nombre, c.apellido FROM Cliente c WHERE c.id=:id", Object[].class)
                .setParameter("id", 1L)
                .getSingleResult();

        Long id = (Long) clienteCamposPersonalizado[0];
        String nombre = (String) clienteCamposPersonalizado[1];
        String apellido = (String) clienteCamposPersonalizado[2];

        System.out.println("id=" + id + ", nombre=" + nombre + ", apellido=" + apellido);

        System.out.println("========== consulta por campos personalizados lista ==========");
        List<Object[]> listaClienteCamposPersonalizado = entityManager.createQuery("SELECT c.id, c.nombre, c.apellido FROM Cliente c", Object[].class)
                .getResultList();

        listaClienteCamposPersonalizado.forEach(reg -> {
            Long idCli = (Long) reg[0];
            String nombreCli = (String) reg[1];
            String apellidoCli = (String) reg[2];
            System.out.println("id=" + idCli + ", nombre=" + nombreCli + ", apellido=" + apellidoCli);

        });

        System.out.println("========== consulta por cliente y forma de pago ==========");
        List<Object[]> registros = entityManager.createQuery("SELECT c, c.formaPago FROM Cliente c", Object[].class)
                .getResultList();

        registros.forEach((reg) -> {
            Cliente c = (Cliente) reg[0];
            String formaPago = (String) reg[1];

            System.out.println("formatoPago=" + formaPago + ", cliente=" + c);
        });

        System.out.println("========== consulta que puebla y devuelve objecto de una clase personalizada ==========");

        clientes = entityManager.createQuery("SELECT new Cliente(c.nombre, c.apellido) from Cliente c", Cliente.class)
                .getResultList();

        clientes.forEach(System.out::println);

        entityManager.close();
    }
}
