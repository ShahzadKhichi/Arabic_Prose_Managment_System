package com.apms.launch;

import java.sql.Connection;
import com.apms.dal.DBConnection;
import com.apms.dal.AuthorDAO;
import com.apms.dto.AuthorDTO;

public class Launch {

    public static void main(String[] args) {
        try {
            // Initialize database connection
            DBConnection dbConnection = DBConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            // Create AuthorDAO instance
            AuthorDAO authorDAO = new AuthorDAO();

            // Dummy data with Arabic characters
            AuthorDTO author = new AuthorDTO(1, "محمد أحمد", "سيرة ذاتية بالعربية");
            
            // Test CREATE
            boolean created = authorDAO.create(author);
            System.out.println("Author created: " + created + ", ID: " + author.getAuthorId());

            // Test READ
            System.out.println("All authors:");
            for (AuthorDTO a : authorDAO.readAll()) {
                System.out.println("ID: " + a.getAuthorId() + ", Name: " + a.getName() + ", Bio: " + a.getBiography());
            }

            // Test UPDATE
            author.setBiography("سيرة ذاتية محدثة");
            boolean updated = authorDAO.update(author);
            System.out.println("Author updated: " + updated);
//
            // Test DELETE
            boolean deleted = authorDAO.delete(author.getAuthorId());
            System.out.println("Author deleted: " + deleted);

            // Close connection
            dbConnection.closeConnection();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}