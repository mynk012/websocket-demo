package com.jainm.websocketdemo.service;

import org.springframework.data.util.Pair;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Chat {

    String sender;
    String receiver;
    String message;
    Timestamp timeStamp;

    public Chat(String sender, String receiver, String message, Timestamp timeStamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", message='" + message + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
}
