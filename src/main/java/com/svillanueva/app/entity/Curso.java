package com.svillanueva.app.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String prefesor;


    @ManyToMany(mappedBy = "cursos")
    private List<Alumno> alumnos;

    public Curso() {
        alumnos = new ArrayList<>();
    }

    public Curso(String titulo, String prefesor) {
        this();
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

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(id, curso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
