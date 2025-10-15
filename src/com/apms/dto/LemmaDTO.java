package com.apms.dto;

public class LemmaDTO {
    private int lemmaId;
    private String lemmaForm;

    public LemmaDTO(int lemmaId, String lemmaForm) {
        this.lemmaId = lemmaId;
        this.lemmaForm = lemmaForm;
    }

    // Getters and Setters
    public int getLemmaId() { return lemmaId; }
    public String getLemmaForm() { return lemmaForm; }
    public void setLemmaForm(String lemmaForm) { this.lemmaForm = lemmaForm; }
}
