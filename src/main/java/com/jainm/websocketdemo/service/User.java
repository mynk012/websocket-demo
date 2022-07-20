package com.jainm.websocketdemo.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class User {

    private String userName;
    private String contact;

    //private Map<String,Chat> messageQueue= new HashMap<String,Chat>();
    private Map<String,HashMap<String, LinkedList<Chat>>> messageQueue;


    public Map<String, HashMap<String, LinkedList<Chat>>> getMessageQueue() {
        return messageQueue;
    }

    public void setMessageQueue(Map<String, HashMap<String, LinkedList<Chat>>> messageQueue) {
        this.messageQueue = messageQueue;
    }

    public User(String userName, String contact, Map<String, HashMap<String, LinkedList<Chat>>> messageQueue) {
        this.userName=userName;
        this.contact=contact;
        this.messageQueue=messageQueue;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


}
