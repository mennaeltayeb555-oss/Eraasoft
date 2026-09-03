
package org.example.hibernet.task4.task1;

import org.example.hibernet.task4.task1.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

    public static void main(String[] args) {

        // ==========================================
        // 1. SAVE
        // ==========================================

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        Player player = new Player();

        player.setName("Messi");
        player.setAge("39");
        player.setStatus(true);

        session.persist(player);

        transaction.commit();

        session.close();

        System.out.println("Player saved successfully!");
        System.out.println("Player ID = " + player.getId());


        // ==========================================
        // 2. GET
        // ==========================================

        session = HibernateUtil.getSessionFactory().openSession();

        Player playerFromDatabase =
                session.get(Player.class, player.getId());

        if (playerFromDatabase != null) {

            System.out.println("Player found!");
            System.out.println("ID: " + playerFromDatabase.getId());
            System.out.println("Name: " + playerFromDatabase.getName());
            System.out.println("Age: " + playerFromDatabase.getAge());
            System.out.println("Status: " + playerFromDatabase.isStatus());

        } else {

            System.out.println("Player not found!");
        }

        session.close();


        // ==========================================
        // 3. UPDATE
        // ==========================================

        session = HibernateUtil.getSessionFactory().openSession();

        transaction = session.beginTransaction();

        Player playerToUpdate =
                session.get(Player.class, player.getId());

        if (playerToUpdate != null) {

            playerToUpdate.setName("Cristiano Ronaldo");
            playerToUpdate.setAge("41");
            playerToUpdate.setStatus(false);

            session.merge(playerToUpdate);

            System.out.println("Player updated successfully!");

        } else {

            System.out.println("Player not found!");
        }

        transaction.commit();

        session.close();


        // ==========================================
        // 4. DELETE
        // ==========================================

        session = HibernateUtil.getSessionFactory().openSession();

        transaction = session.beginTransaction();

        Player playerToDelete =
                session.get(Player.class, player.getId());

        if (playerToDelete != null) {

            session.remove(playerToDelete);

            System.out.println("Player deleted successfully!");

        } else {

            System.out.println("Player not found!");
        }

        transaction.commit();

        session.close();


        // ==========================================
        // CLOSE SESSION FACTORY
        // ==========================================

        HibernateUtil.getSessionFactory().close();

    }
}
