package com.example.admin.chat;

/**
 * Created by ADMIN on 4/17/2017.
 */

public class Messenger {
    public Messenger(String message, String id) {
        this.message = message;
        this.id = id;
    }

    public Messenger() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    String id;
    String message;

}
