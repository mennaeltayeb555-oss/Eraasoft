package org.example.hibernet.task6;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();


        // =====================================================
        // 1. USER + USER DETAILS
        // WITH CASCADE
        // =====================================================

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();


        User user = new User();

        user.setName("Menna");
        user.setAge(22);


        UserDetails details = new UserDetails();

        details.setAddress("Cairo");
        details.setPhone("01000000000");


        user.setUserDetails(details);
        details.setUser(user);


        // WITH CASCADE
        // Save User only

        session.persist(user);


        transaction.commit();

        session.close();


        // =====================================================
        // 2. USER + FRIENDS
        // WITH CASCADE
        // =====================================================

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();


        User user2 = new User();

        user2.setName("Ahmed");
        user2.setAge(25);


        Friend friend1 = new Friend();

        friend1.setName("Ali");


        Friend friend2 = new Friend();

        friend2.setName("Omar");


        user2.addFriend(friend1);
        user2.addFriend(friend2);


        // Cascade saves Friends automatically

        session.persist(user2);


        transaction.commit();

        session.close();


        // =====================================================
        // 3. USER + POST
        // WITH CASCADE
        // =====================================================

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();


        User user3 = new User();

        user3.setName("Sara");
        user3.setAge(23);


        Post post = new Post();

        post.setHeader("My Post");
        post.setContent("Hello Hibernate");


        user3.addPost(post);


        // Cascade saves Post automatically

        session.persist(user3);


        transaction.commit();

        session.close();


        // =====================================================
        // CLOSE
        // =====================================================

        sessionFactory.close();
    }
}
/*public class Main {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();


        // =====================================================
        // 1. ADD USER WITH USER DETAILS
        // WITHOUT CASCADE
        // =====================================================

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();


        // Create User
        User user1 = new User();
        user1.setName("Menna");
        user1.setAge(22);


        // Create UserDetails
        UserDetails userDetails = new UserDetails();
        userDetails.setAddress("Cairo");
        userDetails.setPhone("01000000000");


        // Connect User with UserDetails
        user1.setUserDetails(userDetails);
        userDetails.setUser(user1);


        // WITHOUT CASCADE
        // We must save both objects manually

        session.persist(userDetails);
        session.persist(user1);


        transaction.commit();

        session.close();


        System.out.println("====================================");
        System.out.println("User + UserDetails saved");
        System.out.println("====================================");


        // =====================================================
        // 2. ADD USER WITH FRIENDS
        // WITHOUT CASCADE
        // =====================================================

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();


        // Create User
        User user2 = new User();
        user2.setName("Ahmed");
        user2.setAge(25);


        // Create Friends
        Friend friend1 = new Friend();
        friend1.setName("Ali");

        Friend friend2 = new Friend();
        friend2.setName("Omar");


        // Connect User with Friends
        user2.getFriends().add(friend1);
        user2.getFriends().add(friend2);

        // Also update the other side
        friend1.getUsers().add(user2);
        friend2.getUsers().add(user2);


        // WITHOUT CASCADE
        // Save everything manually

        session.persist(friend1);
        session.persist(friend2);
        session.persist(user2);


        transaction.commit();

        session.close();


        System.out.println("====================================");
        System.out.println("User + Friends saved");
        System.out.println("====================================");


        // =====================================================
        // 3. ADD USER WITH POST
        // WITHOUT CASCADE
        // =====================================================

        session = sessionFactory.openSession();

        transaction = session.beginTransaction();


        // Create User
        User user3 = new User();
        user3.setName("Sara");
        user3.setAge(23);


        // Create Post
        Post post = new Post();
        post.setHeader("My First Post");
        post.setContent("Hello from Hibernate");


        // Connect Post with User
        post.setUser(user3);

        // Also update User side
        user3.getPosts().add(post);


        // WITHOUT CASCADE
        // Save both manually

        session.persist(user3);
        session.persist(post);


        transaction.commit();

        session.close();


        System.out.println("====================================");
        System.out.println("User + Post saved");
        System.out.println("====================================");


        // =====================================================
        // 4. GET USER WITH USER DETAILS
        // FOR LAZY TEST
        // =====================================================

        session = sessionFactory.openSession();


        User userFromDatabase =
                session.get(User.class, user1.getId());


        System.out.println("====================================");
        System.out.println("LAZY TEST");
        System.out.println("====================================");

        System.out.println(
                "User name: " + userFromDatabase.getName()
        );


        // UserDetails will be accessed here
        System.out.println(
                "Address: "
                        + userFromDatabase
                        .getUserDetails()
                        .getAddress()
        );


        session.close();


        // =====================================================
        // 5. GET USER WITH USER DETAILS
        // FOR EAGER TEST
        // =====================================================

        session = sessionFactory.openSession();


        User userFromDatabase2 =
                session.get(User.class, user1.getId());


        System.out.println("====================================");
        System.out.println("EAGER TEST");
        System.out.println("====================================");

        System.out.println(
                "User name: "
                        + userFromDatabase2.getName()
        );


        System.out.println(
                "Address: "
                        + userFromDatabase2
                        .getUserDetails()
                        .getAddress()
        );


        session.close();


        // =====================================================
        // CLOSE SESSION FACTORY
        // =====================================================

        sessionFactory.close();
    }
}*/
