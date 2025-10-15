package com.apms.dal;

import com.apms.dto.BookDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete JDBC implementation of the IBookDAO interface.
 * Handles CRUD operations for the Book entity.
 */
public class BookDAO implements IBookDAO {

    private static final String INSERT_BOOK = "INSERT INTO Book (title, author_id, era) VALUES (?, ?, ?)";
    
    // Uses JOIN to fetch the author's name (A.name) to populate the BookDTO for the UI/BLL
    private static final String SELECT_ALL_BOOKS = 
            "SELECT B.book_id, B.title, B.author_id, B.era, A.name AS author_name " + 
            "FROM Book B JOIN Author A ON B.author_id = A.author_id " +
            "ORDER BY B.title";
            
    private static final String UPDATE_BOOK = "UPDATE Book SET title = ?, author_id = ?, era = ? WHERE book_id = ?";
    private static final String DELETE_BOOK = "DELETE FROM Book WHERE book_id = ?";

    private final Connection conn;

    public BookDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean create(BookDTO book) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(INSERT_BOOK, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, book.getTitle());
            stmt.setInt(2, book.getAuthorId());
            stmt.setString(3, book.getEra());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        // Set the auto-generated ID back to the DTO
                        book.setBookId(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;
        }
    }

    @Override
    public List<BookDTO> readAll() throws SQLException {
        List<BookDTO> books = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_BOOKS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                books.add(new BookDTO(
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getInt("author_id"),
                    rs.getString("era"),
                    rs.getString("author_name") // Populates the auxiliary DTO field
                ));
            }
        }
        return books;
    }

    @Override
    public boolean update(BookDTO book) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(UPDATE_BOOK)) {
            stmt.setString(1, book.getTitle());
            stmt.setInt(2, book.getAuthorId());
            stmt.setString(3, book.getEra());
            stmt.setInt(4, book.getBookId());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int bookId) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(DELETE_BOOK)) {
            stmt.setInt(1, bookId);
            return stmt.executeUpdate() > 0;
        }
    }
}