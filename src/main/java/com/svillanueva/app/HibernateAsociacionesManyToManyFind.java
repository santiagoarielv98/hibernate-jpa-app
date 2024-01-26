package com.svillanueva.app;

import com.svillanueva.app.entity.Alumno;
import com.svillanueva.app.entity.Curso;
import com.svillanueva.app.utils.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.Arrays;

public class HibernateAsociacionesManyToManyFind {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction()
                    .begin();

            Alumno alumno1 = em.find(Alumno.class, 1L);
            Alumno alumno2 = em.find(Alumno.class, 2L);


            Curso curso1 = em.find(Curso.class, 1L);
            Curso curso2 = em.find(Curso.class, 2L);

            alumno1.setCursos(Arrays.asList(curso1, curso2));

            alumno2.getCursos()
                    .add(curso1);

            System.out.println(alumno1);
            System.out.println(alumno2);

            em.getTransaction()
                    .commit();

            em.getTransaction()
                    .begin();

            Curso cursoExistente = em.find(Curso.class, 3L);
            alumno1.getCursos()
                    .remove(cursoExistente);

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
