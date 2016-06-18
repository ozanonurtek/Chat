package com.example.ozan.chat;

/**
 * Class that represent the message.
 */
public class Chat {
    private String username;
    private String message;
    private String id;

    public Chat() {
    }

    public Chat(String username ,String message, String id) {
        this.username = username;
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
