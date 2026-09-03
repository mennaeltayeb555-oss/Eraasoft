package org.example.hibernet.task6;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {

        Configuration configuration = new Configuration();

        configuration.configure("hibernate.cfg.xml");

        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(UserDetails.class);
        configuration.addAnnotatedClass(Friend.class);
        configuration.addAnnotatedClass(Post.class);
        configuration.addAnnotatedClass(
                org.example.hibernet.task6.inheritance.Person.class
        );

        configuration.addAnnotatedClass(
                org.example.hibernet.task6.inheritance.Student.class
        );

        configuration.addAnnotatedClass(
                org.example.hibernet.task6.inheritance.Teacher.class
        );

        sessionFactory =
                configuration.buildSessionFactory();
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}