package com.example.enumeration;

public enum ProjectStatus {
    NotStarted("Not started"),
    InDevelopment("In development"),
    Deployed("Deployed"),
    Stopped("Stopped"),
    InMaintenance("In maintenance"),
    Abandoned("Abandoned");

    private final String label;

    ProjectStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
