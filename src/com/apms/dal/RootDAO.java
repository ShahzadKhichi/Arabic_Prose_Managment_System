package com.apms.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.apms.dto.RootDTO;

public class RootDAO implements IRootDAO {
    private Connection conn;

    public RootDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean addRoot(String rootForm) throws SQLException {
        String sql = "INSERT INTO Root (root_form) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, rootForm);
            int rowsAffected = pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    // Root ID is auto-generated
                }
            }
            return rowsAffected > 0;
        }
    }

    @Override
    public List<RootDTO> getAllRoots() throws SQLException {
        List<RootDTO> roots = new ArrayList<>();
        String sql = "SELECT * FROM Root";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                roots.add(new RootDTO(
                    rs.getInt("root_id"),
                    rs.getString("root_form")
                ));
            }
        }
        return roots;
    }

    @Override
    public RootDTO getRootById(int rootId) throws SQLException {
        String sql = "SELECT * FROM Root WHERE root_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, rootId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new RootDTO(
                        rs.getInt("root_id"),
                        rs.getString("root_form")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public boolean updateRoot(int rootId, String rootForm) throws SQLException {
        String sql = "UPDATE Root SET root_form = ? WHERE root_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, rootForm);
            pstmt.setInt(2, rootId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public boolean deleteRoot(int rootId) throws SQLException {
        String sql = "DELETE FROM Root WHERE root_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, rootId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}