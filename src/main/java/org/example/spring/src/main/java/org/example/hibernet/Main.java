package org.example.hibernet;

import org.example.hibernet.model.Teacher;
import org.example.hibernet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        try {
            Teacher teacher = new Teacher("menna", 20, "Cairo, Egypt");

            session.beginTransaction();
            session.persist(teacher);
            session.getTransaction().commit();

            System.out.println("Teacher saved: " + teacher);

        } finally {
            session.close();
            factory.close();
        }
    }
}
