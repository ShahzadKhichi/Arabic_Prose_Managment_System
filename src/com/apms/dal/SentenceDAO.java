package com.apms.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.apms.dto.SentenceDTO;

public class SentenceDAO implements ISentenceDAO {
    private Connection conn;

    public SentenceDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insertSentence(SentenceDTO s) {
        String sql = "INSERT INTO Sentence (prose_id, sentence_number, text, text_diacritized, translation, notes) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, s.getProseId());
            ps.setInt(2, s.getSentenceNumber());
            ps.setString(3, s.getText());
            ps.setString(4, s.getTextDiacritized());
            ps.setString(5, s.getTranslation());
            ps.setString(6, s.getNotes());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public SentenceDTO getSentenceById(int id) {
        String sql = "SELECT * FROM Sentence WHERE sentence_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new SentenceDTO(
                        rs.getInt("sentence_id"),
                        rs.getInt("prose_id"),
                        rs.getInt("sentence_number"),
                        rs.getString("text"),
                        rs.getString("text_diacritized"),
                        rs.getString("translation"),
                        rs.getString("notes")
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<SentenceDTO> getAllSentences() {
        List<SentenceDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Sentence";
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new SentenceDTO(
                        rs.getInt("sentence_id"),
                        rs.getInt("prose_id"),
                        rs.getInt("sentence_number"),
                        rs.getString("text"),
                        rs.getString("text_diacritized"),
                        rs.getString("translation"),
                        rs.getString("notes")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean updateSentence(SentenceDTO s) {
        String sql = "UPDATE Sentence SET prose_id=?, sentence_number=?, text=?, text_diacritized=?, translation=?, notes=? WHERE sentence_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, s.getProseId());
            ps.setInt(2, s.getSentenceNumber());
            ps.setString(3, s.getText());
            ps.setString(4, s.getTextDiacritized());
            ps.setString(5, s.getTranslation());
            ps.setString(6, s.getNotes());
            ps.setInt(7, s.getSentenceId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean deleteSentence(int id) {
        String sql = "DELETE FROM Sentence WHERE sentence_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
