package com.svillanueva.app;

import com.svillanueva.app.entity.Alumno;
import com.svillanueva.app.entity.Curso;
import com.svillanueva.app.utils.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.Arrays;

public class HibernateAsociacionesManyToMany {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction()
                    .begin();

            Alumno alumno1 = new Alumno("Cata", "Edu");

            Alumno alumno2 = new Alumno("Jano", "Fernando");

            Curso curso1 = new Curso("Curso Java", "Andres");
            Curso curso2 = new Curso("Curso Hibernate y JPA", "Andres");

            alumno1.setCursos(Arrays.asList(curso1, curso2));

            alumno2.getCursos()
                    .add(curso1);

            em.persist(alumno1);
            em.persist(alumno2);

            System.out.println(alumno1);
            System.out.println(alumno2);

            em.getTransaction()
                    .commit();
        } catch (Exception e) {
            em.getTransaction()
                    .rollback();
//            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
