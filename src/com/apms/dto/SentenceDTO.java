package com.apms.dto;

public class SentenceDTO {
    private int sentenceId;
    private int proseId;
    private int sentenceNumber;
    private String text;
    private String textDiacritized;
    private String translation;
    private String notes;

    // --- Constructors ---
    public SentenceDTO() {}

    public SentenceDTO(int sentenceId, int proseId, int sentenceNumber, String text,
                       String textDiacritized, String translation, String notes) {
        this.sentenceId = sentenceId;
        this.proseId = proseId;
        this.sentenceNumber = sentenceNumber;
        this.text = text;
        this.textDiacritized = textDiacritized;
        this.translation = translation;
        this.notes = notes;
    }

    // --- Getters & Setters ---
    public int getSentenceId() { return sentenceId; }
    public void setSentenceId(int sentenceId) { this.sentenceId = sentenceId; }

    public int getProseId() { return proseId; }
    public void setProseId(int proseId) { this.proseId = proseId; }

    public int getSentenceNumber() { return sentenceNumber; }
    public void setSentenceNumber(int sentenceNumber) { this.sentenceNumber = sentenceNumber; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getTextDiacritized() { return textDiacritized; }
    public void setTextDiacritized(String textDiacritized) { this.textDiacritized = textDiacritized; }

    public String getTranslation() { return translation; }
    public void setTranslation(String translation) { this.translation = translation; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    @Override
    public String toString() {
        return "SentenceDTO [id=" + sentenceId + ", proseId=" + proseId +
                ", number=" + sentenceNumber + ", text=" + text + "]";
    }
}
