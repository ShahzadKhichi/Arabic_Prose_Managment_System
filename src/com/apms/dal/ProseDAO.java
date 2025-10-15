package com.apms.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.apms.dto.ProseDTO;

public class ProseDAO implements IProseDAO {
    private Connection conn;

    public ProseDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insertProse(ProseDTO prose) {
        String sql = "INSERT INTO Prose (book_id, title, description) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, prose.getBookId());
            ps.setString(2, prose.getTitle());
            ps.setString(3, prose.getDescription());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ProseDTO getProseById(int id) {
        String sql = "SELECT * FROM Prose WHERE prose_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ProseDTO(
                        rs.getInt("prose_id"),
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<ProseDTO> getAllProse() {
        List<ProseDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Prose";
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new ProseDTO(
                        rs.getInt("prose_id"),
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean updateProse(ProseDTO prose) {
        String sql = "UPDATE Prose SET book_id=?, title=?, description=? WHERE prose_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, prose.getBookId());
            ps.setString(2, prose.getTitle());
            ps.setString(3, prose.getDescription());
            ps.setInt(4, prose.getProseId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean deleteProse(int id) {
        String sql = "DELETE FROM Prose WHERE prose_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
