package com.apms.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.apms.dto.AuthorDTO;

/**
 * Concrete JDBC implementation of the IAuthorDAO interface.
 * Implements single-row CRUD operations without batch queries.
 */
public class AuthorDAO implements IAuthorDAO {

    private static final String INSERT_AUTHOR = "INSERT INTO Author (name, biography) VALUES (?, ?)";
    private static final String SELECT_ALL_AUTHORS = "SELECT * FROM Author ORDER BY name";
    private static final String UPDATE_AUTHOR = "UPDATE Author SET name = ?, biography = ? WHERE author_id = ?";
    private static final String DELETE_AUTHOR = "DELETE FROM Author WHERE author_id = ?";

    @Override
    public boolean create(AuthorDTO author) throws SQLException {
        // Create (Insert) a single Author record
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_AUTHOR, Statement.RETURN_GENERATED_KEYS)) { 
        	
        	System.out.println( author.getName()+"    ***  "+ author.getBiography());        	
        	
            stmt.setString(1, author.getName());
            stmt.setString(2, author.getBiography());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        // Set the auto-generated ID back to the DTO
                        author.setAuthorId(rs.getInt(1)); 
                    }
                }
                return true;
            }
            return false;
        }
    }

    @Override
    public List<AuthorDTO> readAll() throws SQLException {
        // Read (Retrieve) all Authors
        List<AuthorDTO> authors = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_AUTHORS);
             ResultSet rs = stmt.executeQuery()) { 

            while (rs.next()) {
                authors.add(new AuthorDTO(
                    rs.getInt("author_id"),
                    rs.getString("name"),
                    rs.getString("biography")
                ));
            }
        }
        return authors;
    }

    @Override
    public boolean update(AuthorDTO author) throws SQLException {
        // Update an existing Author record
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_AUTHOR)) {

            stmt.setString(1, author.getName());
            stmt.setString(2, author.getBiography());
            stmt.setInt(3, author.getAuthorId());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int authorId) throws SQLException {
        // Delete an Author record
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_AUTHOR)) {
             
            stmt.setInt(1, authorId);
            return stmt.executeUpdate() > 0;
        }
    }
}