package com.prajwal.daoapp;

import java.sql.SQLException;

/** Runs a simple demonstration of every UserDAO CRUD operation. */
public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO(DBConnection.getDataSource());

        try {
            System.out.println("=== CREATE ===");
            User newUser = new User("Amit Sharma", "amit.demo@example.com");
            int generatedId = userDAO.create(newUser);
            System.out.println("Created user with ID: " + generatedId);

            System.out.println("\n=== READ BY ID ===");
            User found = userDAO.findById(generatedId);
            System.out.println(found != null ? found : "User not found");

            System.out.println("\n=== UPDATE ===");
            if (found != null) {
                found.setName("Amit Kumar");
                found.setEmail("amit.kumar.demo@example.com");
                System.out.println("Updated: " + userDAO.update(found));
            }

            System.out.println("\n=== READ ALL ===");
            for (User user : userDAO.findAll()) {
                System.out.println(user);
            }

            System.out.println("\n=== DELETE ===");
            System.out.println("Deleted: " + userDAO.delete(generatedId));

            System.out.println("\n=== VERIFY AFTER DELETE ===");
            System.out.println(userDAO.findById(generatedId) == null
                    ? "User record no longer exists."
                    : "User record still exists.");
        } catch (SQLException exception) {
            System.err.println("Database operation failed: " + exception.getMessage());
            exception.printStackTrace();
        } finally {
            DBConnection.close();
        }
    }
}
