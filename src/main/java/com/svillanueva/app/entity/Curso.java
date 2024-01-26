package com.svillanueva.app.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String prefesor;

    public Curso() {
    }

    public Curso(String titulo, String prefesor) {
        this.titulo = titulo;
        this.prefesor = prefesor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPrefesor() {
        return prefesor;
    }

    public void setPrefesor(String prefesor) {
        this.prefesor = prefesor;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", prefesor='" + prefesor + '\'' +
                '}';
    }
}
