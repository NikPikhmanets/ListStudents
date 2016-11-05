package com.example.studentslist;


public class ProfileInfo {
    private String key;
    private String description;

    public ProfileInfo(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }
}
