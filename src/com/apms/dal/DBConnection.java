package com.apms.dal;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private static final String URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12802693";
    private static final String USER = "sql12802693";
    private static final String PASSWORD = "wwnV6D6Lkq";

   
    private static DBConnection instance;
    private static Connection connection;

    
    private DBConnection() {
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");

          
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Database connection established successfully!");
            intializeDB();
    

        } catch (ClassNotFoundException e) {
        	
            System.out.println("❌ MySQL JDBC Driver not found!");
            e.printStackTrace();
            
        } catch (SQLException e) {
        	
            System.out.println("❌ Failed to connect to the database!");
            e.printStackTrace();
            
        }
    }
    
    public void intializeDB()
    {
		    	
		try {
			
	        Statement stmt=connection.createStatement();
            
	         // Author table
	            stmt.addBatch("CREATE TABLE IF NOT EXISTS Author (" +
	                    "author_id INT PRIMARY KEY AUTO_INCREMENT, " +
	                    "name VARCHAR(255) NOT NULL, " +
	                    "biography TEXT)");

	            // Book table
	            stmt.addBatch("CREATE TABLE IF NOT EXISTS Book (" +
	                    "book_id INT PRIMARY KEY AUTO_INCREMENT, " +
	                    "title VARCHAR(255) NOT NULL, " +
	                    "author_id INT, " +
	                    "era VARCHAR(50), " +
	                    "FOREIGN KEY (author_id) REFERENCES Author(author_id) ON DELETE SET NULL)");

	            // Prose table
	            stmt.addBatch("CREATE TABLE IF NOT EXISTS Prose (" +
	                    "prose_id INT PRIMARY KEY AUTO_INCREMENT, " +
	                    "book_id INT, " +
	                    "title VARCHAR(255), " +
	                    "description TEXT, " +
	                    "FOREIGN KEY (book_id) REFERENCES Book(book_id) ON DELETE SET NULL)");

	            // Sentence table (updated to reference Prose)
	            stmt.addBatch("CREATE TABLE IF NOT EXISTS Sentence (" +
	                    "sentence_id INT PRIMARY KEY AUTO_INCREMENT, " +
	                    "prose_id INT, " +
	                    "sentence_number INT NOT NULL, " +
	                    "text TEXT NOT NULL, " +
	                    "text_diacritized TEXT, " +
	                    "translation TEXT, " +
	                    "notes TEXT, " +
	                    "FOREIGN KEY (prose_id) REFERENCES Prose(prose_id) ON DELETE SET NULL)");

	            // Token table (updated to reference Sentence)
	            stmt.addBatch("CREATE TABLE IF NOT EXISTS Token (" +
	                    "token_id INT PRIMARY KEY AUTO_INCREMENT, " +
	                    "surface_form VARCHAR(255) NOT NULL, " +
	                    "sentence_id INT, " +
	                    "position INT NOT NULL, " +
	                    "FOREIGN KEY (sentence_id) REFERENCES Sentence(sentence_id) ON DELETE SET NULL)");

	            // Lemma table
	            stmt.addBatch("CREATE TABLE IF NOT EXISTS Lemma (" +
	                    "lemma_id INT PRIMARY KEY AUTO_INCREMENT, " +
	                    "lemma_form VARCHAR(255) NOT NULL)");

	            // Root table
	            stmt.addBatch("CREATE TABLE IF NOT EXISTS Root (" +
	                    "root_id INT PRIMARY KEY AUTO_INCREMENT, " +
	                    "root_form VARCHAR(10) NOT NULL)");

	            // TokenLemma table
	            stmt.addBatch("CREATE TABLE IF NOT EXISTS TokenLemma (" +
	                    "token_id INT, " +
	                    "lemma_id INT, " +
	                    "PRIMARY KEY (token_id, lemma_id), " +
	                    "FOREIGN KEY (token_id) REFERENCES Token(token_id) ON DELETE CASCADE, " +
	                    "FOREIGN KEY (lemma_id) REFERENCES Lemma(lemma_id) ON DELETE CASCADE)");

	            // LemmaRoot table
	            stmt.addBatch("CREATE TABLE IF NOT EXISTS LemmaRoot (" +
	                    "lemma_id INT, " +
	                    "root_id INT, " +
	                    "PRIMARY KEY (lemma_id, root_id), " +
	                    "FOREIGN KEY (lemma_id) REFERENCES Lemma(lemma_id) ON DELETE CASCADE, " +
	                    "FOREIGN KEY (root_id) REFERENCES Root(root_id) ON DELETE CASCADE)");

	            stmt.executeBatch();
	            
	            
			/*
			 * 
			 * -- Create database
			CREATE DATABASE IF NOT EXISTS arabic_prose_management;
			USE arabic_prose_management;
			
			-- Create Author table
			CREATE TABLE IF NOT EXISTS Author (
			    author_id INT PRIMARY KEY AUTO_INCREMENT,
			    name VARCHAR(255) NOT NULL,
			    biography TEXT
			);
			
			-- Create Book table
			CREATE TABLE IF NOT EXISTS Book (
			    book_id INT PRIMARY KEY AUTO_INCREMENT,
			    title VARCHAR(255) NOT NULL,
			    author_id INT,
			    era VARCHAR(50),
			    FOREIGN KEY (author_id) REFERENCES Author(author_id) ON DELETE SET NULL
			);
			
			-- Create Sentence table
			CREATE TABLE IF NOT EXISTS Sentence (
			    sentence_id INT PRIMARY KEY AUTO_INCREMENT,
			    book_id INT,
			    sentence_number INT NOT NULL,
			    text TEXT NOT NULL,
			    text_diacritized TEXT,
			    translation TEXT,
			    notes TEXT,
			    FOREIGN KEY (book_id) REFERENCES Book(book_id) ON DELETE SET NULL
			);
			
			-- Create Token table
			CREATE TABLE IF NOT EXISTS Token (
			    token_id INT PRIMARY KEY AUTO_INCREMENT,
			    surface_form VARCHAR(255) NOT NULL,
			    sentence_id INT,
			    position INT NOT NULL,
			    FOREIGN KEY (sentence_id) REFERENCES Sentence(sentence_id) ON DELETE SET NULL
			);
			
			-- Create Lemma table
			CREATE TABLE IF NOT EXISTS Lemma (
			    lemma_id INT PRIMARY KEY AUTO_INCREMENT,
			    lemma_form VARCHAR(255) NOT NULL
			);
			
			-- Create Root table
			CREATE TABLE IF NOT EXISTS Root (
			    root_id INT PRIMARY KEY AUTO_INCREMENT,
			    root_form VARCHAR(10) NOT NULL
			);
			
			-- Create TokenLemma table (junction table for Token and Lemma)
			CREATE TABLE IF NOT EXISTS TokenLemma (
			    token_id INT,
			    lemma_id INT,
			    PRIMARY KEY (token_id, lemma_id),
			    FOREIGN KEY (token_id) REFERENCES Token(token_id) ON DELETE CASCADE,
			    FOREIGN KEY (lemma_id) REFERENCES Lemma(lemma_id) ON DELETE CASCADE
			);
			
			-- Create LemmaRoot table (junction table for Lemma and Root)
			CREATE TABLE IF NOT EXISTS LemmaRoot (
			    lemma_id INT,
			    root_id INT,
			    PRIMARY KEY (lemma_id, root_id),
			    FOREIGN KEY (lemma_id) REFERENCES Lemma(lemma_id) ON DELETE CASCADE,
			    FOREIGN KEY (root_id) REFERENCES Root(root_id) ON DELETE CASCADE
			);
			 * */
	            
	            System.out.println("Tables created successfully or already exist.");
	            
	            stmt.close();
			
		} catch (SQLException e) {
		
				
			e.printStackTrace();
		}
    }

    // Public method to get the singleton instance
    public static DBConnection getInstance() {
    	
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
        }
        
        return instance;
    }

   
    public Connection getConnection() {
        try {
        	
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            
        } catch (SQLException e) {
        	
            e.printStackTrace();
            
        }
        return connection;
    }
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("🔒 Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("⚠️ Failed to close database connection.");
            e.printStackTrace();
        }
    }
}
