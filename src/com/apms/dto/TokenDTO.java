package com.apms.dto;

public class TokenDTO {
    private int tokenId;
    private String surfaceForm;
    private int sentenceId;
    private int position;

    public TokenDTO(int tokenId, String surfaceForm, int sentenceId, int position) {
        this.tokenId = tokenId;
        this.surfaceForm = surfaceForm;
        this.sentenceId = sentenceId;
        this.position = position;
    }

    // Getters and Setters
    public int getTokenId() { return tokenId; }
    public String getSurfaceForm() { return surfaceForm; }
    public int getSentenceId() { return sentenceId; }
    public int getPosition() { return position; }
    public void setSurfaceForm(String surfaceForm) { this.surfaceForm = surfaceForm; }
    public void setSentenceId(int sentenceId) { this.sentenceId = sentenceId; }
    public void setPosition(int position) { this.position = position; }
}