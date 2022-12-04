package com.hello.jpa;

import org.hibernate.Hibernate;

import javax.persistence.*;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member();
            member1.setUsername("hello");

            Member member2 = new Member();
            member2.setUsername("hello1");

            em.persist(member1);
            em.persist(member2);

            em.flush();
            em.clear();

            //Member findMember = em.find(Member.class, member.getId());

            Member m1 = em.getReference(Member.class, member1.getId());

            Hibernate.initialize(m1);

            System.out.println("isLoaded : " + emf.getPersistenceUnitUtil().isLoaded(m1));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
