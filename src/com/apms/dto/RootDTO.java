package com.apms.dto;

public class RootDTO {
    private int rootId;
    private String rootForm;

    public RootDTO(int rootId, String rootForm) {
        this.rootId = rootId;
        this.rootForm = rootForm;
    }

    // Getters and Setters
    public int getRootId() { return rootId; }
    public String getRootForm() { return rootForm; }
    public void setRootForm(String rootForm) { this.rootForm = rootForm; }
}
