package org.example.hibernet.task6.inheritance;

import org.example.hibernet.task6.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class InheritanceMain {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();


        Session session =
                sessionFactory.openSession();


        Transaction transaction =
                session.beginTransaction();


        // ==========================================
        // Create Student
        // ==========================================

        Student student = new Student();

        student.setName("Menna");
        student.setUniversity("Helwan University");


        // ==========================================
        // Create Teacher
        // ==========================================

        Teacher teacher = new Teacher();

        teacher.setName("Ahmed");
        teacher.setSubject("Java");


        // ==========================================
        // Save
        // ==========================================

        session.persist(student);
        session.persist(teacher);


        transaction.commit();


        session.close();


        sessionFactory.close();
    }
}
