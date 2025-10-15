package com.apms.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.apms.dto.LemmaDTO;

public class LemmaDAO implements ILemmaDAO {
    private Connection conn;

    public LemmaDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean addLemma(String lemmaForm) throws SQLException {
        String sql = "INSERT INTO Lemma (lemma_form) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, lemmaForm);
            int rowsAffected = pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    // Lemma ID is auto-generated
                }
            }
            return rowsAffected > 0;
        }
    }

    @Override
    public List<LemmaDTO> getAllLemmas() throws SQLException {
        List<LemmaDTO> lemmas = new ArrayList<>();
        String sql = "SELECT * FROM Lemma";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lemmas.add(new LemmaDTO(
                    rs.getInt("lemma_id"),
                    rs.getString("lemma_form")
                ));
            }
        }
        return lemmas;
    }

    @Override
    public LemmaDTO getLemmaById(int lemmaId) throws SQLException {
        String sql = "SELECT * FROM Lemma WHERE lemma_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, lemmaId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new LemmaDTO(
                        rs.getInt("lemma_id"),
                        rs.getString("lemma_form")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public boolean updateLemma(int lemmaId, String lemmaForm) throws SQLException {
        String sql = "UPDATE Lemma SET lemma_form = ? WHERE lemma_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, lemmaForm);
            pstmt.setInt(2, lemmaId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public boolean deleteLemma(int lemmaId) throws SQLException {
        String sql = "DELETE FROM Lemma WHERE lemma_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, lemmaId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
