package com.TheVision.tv;

public class internetDataStore {
    String email;
    String description;

    internetDataStore() {

    }

    public internetDataStore(String email, String description) {
        this.email = email;
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }
}
