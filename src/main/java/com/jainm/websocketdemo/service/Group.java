package com.jainm.websocketdemo.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Group {

    private String groupName;
    private LinkedList<User> userList= new LinkedList<>();

    public Group(String groupName, LinkedList<User> es) {
        this.groupName=groupName;

    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId='" + groupName + '\'' +
                ", userList=" + userList +
                '}';
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public LinkedList<User> getUserList() {
        return userList;
    }

    public void setUserList(LinkedList<User> userList) {
        this.userList = userList;
    }
}
