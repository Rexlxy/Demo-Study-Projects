package com.example.websocket.models;


import lombok.Data;

import java.io.Serializable;


@Data
public class Message implements Serializable {

    private String clientId;
    private String message;
    private Long sentTime;
    private String receiverId;

    public Message() {

    }

    public Message(String clientId, String message) {
        this.clientId = clientId;
        this.message = message;
        this.sentTime = System.currentTimeMillis();
    }
}


