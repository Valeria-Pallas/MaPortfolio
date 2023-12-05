package com.example.enumeration;

public enum TaskStatus {
    NotStarted("Not started"),
    InDevelopment("In development"),
    Deployed("Deployed"),
    Abandoned("Abandoned");

    private final String label;

    TaskStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
