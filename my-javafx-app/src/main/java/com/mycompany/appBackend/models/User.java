package com.mycompany.appBackend.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

import com.mycompany.appBackend.auth.AuthManager;
import com.mycompany.appBackend.databases.DatabaseConnection;

public class User implements BaseModelInterface<User> {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String secretQuestion;
    private String secretAnswer;

    // Constructors
    public User() {}

    public User(String name, String email, String phone, String password, String secretQuestion, String secretAnswer) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
    }

    public User(int id, String name, String email, String phone, String password, String secretQuestion, String secretAnswer) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }

    // Database operations
    public Boolean create() {
        String sql = "INSERT INTO users(name, email, phone, password, secretQuestion, secretAnswer) VALUES(?,?,?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.name);
            pstmt.setString(2, this.email);
            pstmt.setString(3, this.phone);
            pstmt.setString(4, this.password);
            pstmt.setString(5, this.secretQuestion);
            pstmt.setString(6, this.secretAnswer);

            int affectedRows = pstmt.executeUpdate();

            // Check if a new row was inserted
            if (affectedRows == 0) {
                System.out.println("Creating user failed, no rows affected.");
                return false;

            }else{
                System.out.println("User created successfully.");
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        System.out.println("Generated user ID: " + id);
                        User newUser = new User(id, this.name, this.email, this.phone, this.password, this.secretQuestion, this.secretAnswer);
                        AuthManager.login(newUser);
    
                        System.out.println("Global variable authenticated user has been set");
                        return true;
                    } else {
                        System.out.println("Global variable authenticated user not set no ID obtained.");
                        return false;
                    }
                } 
            }
        } catch (SQLException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
        return false;

    }

    public Boolean createAndNotSetAsAuthenticatedUser() {
        String sql = "INSERT INTO users(name, email, phone, password, secretQuestion, secretAnswer) VALUES(?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.name);
            pstmt.setString(2, this.email);
            pstmt.setString(3, this.phone);
            pstmt.setString(4, this.password);
            pstmt.setString(5, this.secretQuestion);
            pstmt.setString(6, this.secretAnswer);
            pstmt.executeUpdate();
            System.out.println("User created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
        return false;

    }

    public void update() {
        String sql = "UPDATE users SET name=?, email=?, phone=?, password=?, secretQuestion=?, secretAnswer=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.name);
            pstmt.setString(2, this.email);
            pstmt.setString(3, this.phone);
            pstmt.setString(4, this.password);
            pstmt.setString(5, this.secretQuestion);
            pstmt.setString(6, this.secretAnswer);
            pstmt.setInt(7, this.id);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("No user found with ID " + this.id);
            }
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    public void delete() {
        String sql = "DELETE FROM users WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, this.id);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("No user found with ID " + this.id);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

    public User viewById(int id){
        User user = null;
        return user;
    };

    public Stack<User>  view(){
        Stack<User> users = new Stack<User>();
        return users;
    };


}
