package org.example.hibernet.task5;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();


        // =====================================================
        // 1. SAVE FROM MODEL 1
        // Student -> Courses
        // =====================================================

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        // Create Student
        Student student1 = new Student();
        student1.setName("Menna");


        // Create Courses
        Course java = new Course();
        java.setName("Java");

        Course spring = new Course();
        spring.setName("Spring Boot");


        // Connect Student with Courses
        student1.addCourse(java);
        student1.addCourse(spring);


        // No Cascade
        // So we must save the Courses manually
        session.persist(java);
        session.persist(spring);

        // Save Student
        session.persist(student1);


        transaction.commit();

        session.close();

        System.out.println("=================================");
        System.out.println("Student saved with courses!");
        System.out.println("=================================");



        // =====================================================
        // 2. SAVE FROM MODEL 2
        // Course -> Students
        // =====================================================

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();


        // Create another Course
        Course database = new Course();
        database.setName("Database");


        // Create Students
        Student student2 = new Student();
        student2.setName("Ahmed");

        Student student3 = new Student();
        student3.setName("Ali");


        // Connect Students with Course
        student2.addCourse(database);
        student3.addCourse(database);


        // No Cascade
        // Save Course manually
        session.persist(database);

        // Save Students manually
        session.persist(student2);
        session.persist(student3);


        transaction.commit();

        session.close();

        System.out.println("=================================");
        System.out.println("Course saved with students!");
        System.out.println("=================================");



        // =====================================================
        // 3. RETRIEVE FROM MODEL 1
        // Student -> Courses
        // =====================================================

        session = sessionFactory.openSession();


        Student studentFromDatabase =
                session.get(Student.class, student1.getId());


        System.out.println("=================================");
        System.out.println("Retrieve from Student");
        System.out.println("=================================");

        System.out.println(
                "Student: " + studentFromDatabase.getName()
        );


        for (Course course : studentFromDatabase.getCourses()) {

            System.out.println(
                    "Course: " + course.getName()
            );
        }


        session.close();



        // =====================================================
        // 4. RETRIEVE FROM MODEL 2
        // Course -> Students
        // =====================================================

        session = sessionFactory.openSession();


        Course courseFromDatabase =
                session.get(Course.class, database.getId());


        System.out.println("=================================");
        System.out.println("Retrieve from Course");
        System.out.println("=================================");

        System.out.println(
                "Course: " + courseFromDatabase.getName()
        );


        for (Student student : courseFromDatabase.getStudents()) {

            System.out.println(
                    "Student: " + student.getName()
            );
        }


        session.close();



        // =====================================================
        // CLOSE SESSION FACTORY
        // =====================================================

        sessionFactory.close();
    }
}

