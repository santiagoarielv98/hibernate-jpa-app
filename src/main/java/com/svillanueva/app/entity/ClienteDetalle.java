package com.svillanueva.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente_detalles")
public class ClienteDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean prime;

    @Column(name = "putos_acumulados")
    private Long puntosAcumulados;

    @OneToOne
    @JoinColumn("cliente_detalle_id")
    private Cliente cliente;

    public ClienteDetalle() {
    }

    public ClienteDetalle(boolean prime, Long puntosAcumulados) {
        this.prime = prime;
        this.puntosAcumulados = puntosAcumulados;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPrime() {
        return prime;
    }

    public void setPrime(boolean prime) {
        this.prime = prime;
    }

    public Long getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(Long puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", prime=" + prime +
                ", puntosAcumulados=" + puntosAcumulados +
                '}';
    }
}

