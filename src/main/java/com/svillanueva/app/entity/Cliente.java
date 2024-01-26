package com.svillanueva.app.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "cliente_id")
    @JoinTable(name = "tbl_clientes_direcciones", joinColumns = @JoinColumn(name = "id_cliente"), inverseJoinColumns = @JoinColumn(name = "id_direccion"), uniqueConstraints = @UniqueConstraint(columnNames = {"id_direccion"}))
    private List<Direccion> direcciones;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
    private List<Factura> facturas;

    @Column(name = "forma_pago")
    private String formaPago;
    @Embedded
    private final Auditoria auditoria = new Auditoria();

    public Cliente() {
        facturas = new ArrayList<>();
        direcciones = new ArrayList<>();

    }

    public Cliente(String nombre, String apellido) {
        this();
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Cliente(Long id, String nombre, String apellido, String formaPago) {
        this();
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.formaPago = formaPago;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public Auditoria getAuditoria() {
        return auditoria;
    }

    public void addFactura(Factura factura) {
        facturas.add(factura);
        factura.setCliente(this);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", formaPago='" + formaPago + '\'' +
                ", creadoEn=" + this.auditoria.getCreadoEn() +
                ", actualizadoEn=" + this.auditoria.getActualizadoEn() +
                ", factura=" + this.facturas +
                ", direcciones=" + this.direcciones +
                '}';
    }
}
