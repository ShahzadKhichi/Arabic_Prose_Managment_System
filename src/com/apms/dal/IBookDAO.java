package com.apms.dal;

import com.apms.dto.BookDTO;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface defining the CRUD contract for Book data access.
 * The Business Logic Layer (BLL) should depend on this interface.
 */
public interface IBookDAO {
    
    /**
     * Inserts a new Book record into the database.
     * @param book The Book object to insert. The auto-generated ID will be set on this object.
     * @return true if the insertion was successful, false otherwise.
     * @throws SQLException If a database error occurs.
     */
    boolean create(BookDTO book) throws SQLException;
    
    /**
     * Retrieves all Book records, including the associated Author Name via a JOIN.
     * @return A list of all Book objects.
     * @throws SQLException If a database error occurs.
     */
    List<BookDTO> readAll() throws SQLException;
    
    /**
     * Updates an existing Book record in the database.
     * @param book The Book object with updated information (ID must be set).
     * @return true if the update affected one or more rows, false otherwise.
     * @throws SQLException If a database error occurs.
     */
    boolean update(BookDTO book) throws SQLException;
    
    /**
     * Deletes a Book record from the database by ID.
     * @param bookId The ID of the Book to delete.
     * @return true if the deletion was successful, false otherwise.
     * @throws SQLException If a database error occurs (including foreign key violation).
     */
    boolean delete(int bookId) throws SQLException;
}