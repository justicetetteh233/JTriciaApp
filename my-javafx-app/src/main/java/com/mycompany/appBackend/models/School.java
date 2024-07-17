package com.mycompany.appBackend.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

import com.mycompany.appBackend.databases.DatabaseConnection;

public class School implements BaseModelInterface<School> {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String motto;
    private String address;
    private String location;
    private int proprietor;

    // Default constructor
    public School() {
    }

    // Constructor without id
    public School(String name, String email, String phone, String motto, String address, String location, int proprietor) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.motto = motto;
        this.address = address;
        this.location = location;
        this.proprietor = proprietor;
    }

    // Constructor with all properties
    public School(int id, String name, String email, String phone, String motto, String address, String location, int proprietor) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.motto = motto;
        this.address = address;
        this.location = location;
        this.proprietor = proprietor;
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

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getProprietor() {
        return proprietor;
    }

    public void setProprietor(int proprietor) {
        this.proprietor = proprietor;
    }

    // Create a school
    public Boolean create() {
        String sql = "INSERT INTO school(name, email, phone, motto, address, location, proprietor) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.name);
            pstmt.setString(2, this.email);
            pstmt.setString(3, this.phone);
            pstmt.setString(4, this.motto);
            pstmt.setString(5, this.address);
            pstmt.setString(6, this.location);
            pstmt.setInt(7, this.proprietor);
            pstmt.executeUpdate();
            System.out.println("School created successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error creating school: " + e.getMessage());
            return false;
        }
    }

    // Update a school using the id
    public void update() {
        String sql = "UPDATE school SET name=?, email=?, phone=?, motto=?, address=?, location=?, proprietor=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.name);
            pstmt.setString(2, this.email);
            pstmt.setString(3, this.phone);
            pstmt.setString(4, this.motto);
            pstmt.setString(5, this.address);
            pstmt.setString(6, this.location);
            pstmt.setInt(7, this.proprietor);
            pstmt.setInt(8, this.id);
            pstmt.executeUpdate();
            System.out.println("School updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating school: " + e.getMessage());
        }
    }

    // Delete a school using the id
    public void delete() {
        String sql = "DELETE FROM school WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, this.id);
            pstmt.executeUpdate();
            System.out.println("School deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting school: " + e.getMessage());
        }
    }

    // Get list of schools
    public Stack<School> view() {
        Stack<School> schools = new Stack<>();
        String sql = "SELECT * FROM school";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                School school = new School(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("motto"),
                    rs.getString("address"),
                    rs.getString("location"),
                    rs.getInt("proprietor")
                );
                schools.add(school);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching schools: " + e.getMessage());
        }
        return schools;
    }

    // Get a specific school by id
    public School viewById(int id) {
        School school = new School();
        String sql = "SELECT * FROM school WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    school.setId(rs.getInt("id"));
                    school.setName(rs.getString("name"));
                    school.setEmail(rs.getString("email"));
                    school.setPhone(rs.getString("phone"));
                    school.setMotto(rs.getString("motto"));
                    school.setAddress(rs.getString("address"));
                    school.setLocation(rs.getString("location"));
                    school.setProprietor(rs.getInt("proprietor"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching school: " + e.getMessage());
        }
        return school;
    }



}
