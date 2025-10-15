package com.apms.dto;

public class ProseDTO {
    private int proseId;
    private int bookId;
    private String title;
    private String description;

    // --- Constructors ---
    public ProseDTO() {}

    public ProseDTO(int proseId, int bookId, String title, String description) {
        this.proseId = proseId;
        this.bookId = bookId;
        this.title = title;
        this.description = description;
    }

    // --- Getters & Setters ---
    public int getProseId() { return proseId; }
    public void setProseId(int proseId) { this.proseId = proseId; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "ProseDTO [proseId=" + proseId + ", bookId=" + bookId +
               ", title=" + title + ", description=" + description + "]";
    }
}
