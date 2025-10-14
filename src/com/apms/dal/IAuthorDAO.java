package com.apms.dal;

import com.apms.dto.AuthorDTO;
import java.sql.SQLException;
import java.util.List;

public interface IAuthorDAO {
	/**
     * Inserts a new Author record into the database.
     * @param author The Author object to insert. The auto-generated ID will be set on this object.
     * @return true if the insertion was successful, false otherwise.
     * @throws SQLException If a database error occurs.
     */
    boolean create(AuthorDTO author) throws SQLException;
    
    /**
     * Retrieves all Author records from the database.
     * @return A list of all Author objects.
     * @throws SQLException If a database error occurs.
     */
    List<AuthorDTO> readAll() throws SQLException;
    
    /**
     * Updates an existing Author record in the database.
     * @param author The Author object with updated information (ID must be set).
     * @return true if the update affected one or more rows, false otherwise.
     * @throws SQLException If a database error occurs.
     */
    boolean update(AuthorDTO author) throws SQLException;
    
    /**
     * Deletes an Author record from the database by ID.
     * @param authorId The ID of the Author to delete.
     * @return true if the deletion was successful, false otherwise.
     * @throws SQLException If a database error occurs (including foreign key violation).
     */
    boolean delete(int authorId) throws SQLException;
}
