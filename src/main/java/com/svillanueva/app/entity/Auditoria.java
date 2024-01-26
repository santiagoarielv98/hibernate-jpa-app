package com.svillanueva.app.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Embeddable
public class Auditoria {
    @Column(name = "creado_en")
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en")
    private LocalDateTime actualizadoEn;


    @PrePersist
    public void prePersist() {
        System.out.println("Antes de persistir");
        this.creadoEn = LocalDateTime.now();
    }

    @PostPersist
    public void postPersist() {
        System.out.println("Después de persistir");
    }

    @PreUpdate
    public void preUpdate() {
        System.out.println("Antes de actualizar");
        this.actualizadoEn = LocalDateTime.now();
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }

    public void setActualizadoEn(LocalDateTime actualizadoEn) {
        this.actualizadoEn = actualizadoEn;
    }


}
