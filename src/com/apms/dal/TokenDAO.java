package com.apms.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.apms.dto.TokenDTO;

public class TokenDAO implements ITokenDAO {
    private Connection conn;

    public TokenDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean addToken(String surfaceForm, int sentenceId, int position) throws SQLException {
        String sql = "INSERT INTO Token (surface_form, sentence_id, position) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, surfaceForm);
            pstmt.setInt(2, sentenceId);
            pstmt.setInt(3, position);
            int rowsAffected = pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    // Token ID is auto-generated
                }
            }
            return rowsAffected > 0;
        }
    }

    @Override
    public List<TokenDTO> getAllTokens() throws SQLException {
        List<TokenDTO> tokens = new ArrayList<>();
        String sql = "SELECT * FROM Token";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tokens.add(new TokenDTO(
                    rs.getInt("token_id"),
                    rs.getString("surface_form"),
                    rs.getInt("sentence_id"),
                    rs.getInt("position")
                ));
            }
        }
        return tokens;
    }

    @Override
    public TokenDTO getTokenById(int tokenId) throws SQLException {
        String sql = "SELECT * FROM Token WHERE token_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tokenId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new TokenDTO(
                        rs.getInt("token_id"),
                        rs.getString("surface_form"),
                        rs.getInt("sentence_id"),
                        rs.getInt("position")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public boolean updateToken(int tokenId, String surfaceForm, int sentenceId, int position) throws SQLException {
        String sql = "UPDATE Token SET surface_form = ?, sentence_id = ?, position = ? WHERE token_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, surfaceForm);
            pstmt.setInt(2, sentenceId);
            pstmt.setInt(3, position);
            pstmt.setInt(4, tokenId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public boolean deleteToken(int tokenId) throws SQLException {
        String sql = "DELETE FROM Token WHERE token_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tokenId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}