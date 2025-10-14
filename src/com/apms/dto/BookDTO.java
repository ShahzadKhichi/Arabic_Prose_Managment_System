package com.apms.dto;
import java.io.Serializable;

/**
 * Data Transfer Object for the Book entity.
 */
@SuppressWarnings("serial")
public class BookDTO implements Serializable {
    private int bookId;
    private String title;
    private int authorId; // Foreign Key to Author
    private String era;
    
    // Auxiliary field for UI/BLL display (fetched via JOIN)
    private String authorName; 

    // Default Constructor
    public BookDTO() {}

    // Constructor for creating a new Book
    public BookDTO(String title, int authorId, String era) {
        this.title = title;
        this.authorId = authorId;
        this.era = era;
    }
    
    // Constructor for reading/updating (ID comes from DB)
    public BookDTO(int bookId, String title, int authorId, String era) {
        this.bookId = bookId;
        this.title = title;
        this.authorId = authorId;
        this.era = era;
    }
    
    // Constructor for reading/displaying with Author Name
    public BookDTO(int bookId, String title, int authorId, String era, String authorName) {
        this(bookId, title, authorId, era);
        this.authorName = authorName;
    }

    // Getters and Setters
    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getAuthorId() { return authorId; }
    public void setAuthorId(int authorId) { this.authorId = authorId; }

    public String getEra() { return era; }
    public void setEra(String era) { this.era = era; }
    
    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }
    
    @Override
    public String toString() {
        return title;
    }
}