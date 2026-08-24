package org.example.hibernet.util;

import org.example.hibernet.model.Teacher;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {

            Configuration configuration = new Configuration();

            // ---- database connection settings (Postgres) ----
            configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
            configuration.setProperty("hibernate.connection.username", "postgres");
            configuration.setProperty("hibernate.connection.password", "123456");

            // ---- SQL dialect ----
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

            // ---- logging ----
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.format_sql", "true");

            // ---- auto create/update tables ----
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");

            // ---- register entity class (annotation-based, no xml mapping) ----
            configuration.addAnnotatedClass(Teacher.class);

            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
