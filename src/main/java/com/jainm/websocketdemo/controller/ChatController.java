package com.jainm.websocketdemo.controller;

import com.jainm.websocketdemo.service.Chat;
import com.jainm.websocketdemo.service.Group;
import com.jainm.websocketdemo.service.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.springframework.http.RequestEntity.put;

@Controller
public class ChatController {

    Map<String,User> userDetails= new HashMap<String,User>();
    Map<String,Group> groupDetails=new HashMap<String,Group>();

    @PostMapping("/registration/user")
    public ResponseEntity<String> userRegistered(@RequestBody User user){
        if(!userDetails.containsKey(user.getContact())){

            Map<String, HashMap<String, LinkedList<Chat>>> userQueue=new HashMap<>();
            userQueue.put(user.getUserName(),new HashMap<String,LinkedList<Chat>>());

            User user1=new User(user.getUserName(),user.getContact(),userQueue);
            userDetails.put(user.getContact(),user1);

        }

        return new ResponseEntity<String>(user.getUserName()+" is registered", HttpStatus.OK);

    }

    @PostMapping("/createGroup")
    public ResponseEntity<String> createGroup(@RequestBody Group gp){
        if(!groupDetails.containsKey(gp.getGroupName())){
            Group gp1=new Group(gp.getGroupName(),new LinkedList<>());
            groupDetails.put(gp.getGroupName(),gp1);
        }

        return new ResponseEntity<String>(gp.getGroupName()+" is created",HttpStatus.OK);
    }

    @PutMapping("/dialogBox/{userId}")
    public ResponseEntity<String> dialogBox(@RequestBody Chat chat, @PathVariable String userId) throws URISyntaxException {

        if(userDetails.containsKey(userId)) {
            User senderUser = userDetails.get(userId);

            String receiver = chat.getReceiver();
            String sender = senderUser.getContact();
            String msg = chat.getMessage();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            Chat curChat = new Chat(sender, receiver, msg, timestamp);

            //Map<String,Chat> messageQueue=curUser.getMessageQueue();

            User receiverUser=userDetails.get(receiver);

            Map<String, HashMap<String, LinkedList<Chat>>> userInbox = senderUser.getMessageQueue();
            HashMap<String, LinkedList<Chat>> senderChatBox = userInbox.get(senderUser.getUserName());

            if(!senderChatBox.containsKey(receiverUser.getUserName())){
                senderChatBox.put(receiverUser.getUserName(),new LinkedList<>());
                LinkedList<Chat> curUserChat=senderChatBox.get(receiverUser.getUserName());
                curUserChat.add(curChat);
            }
            else {
                LinkedList<Chat> curUserChat = senderChatBox.get(receiverUser.getUserName());
                curUserChat.add(curChat);
            }


            Map<String, HashMap<String, LinkedList<Chat>>> receiverInbox = receiverUser.getMessageQueue();
            HashMap<String,LinkedList<Chat>> receiverChatBox=receiverInbox.get(receiverUser.getUserName());

            if(!receiverChatBox.containsKey(senderUser.getUserName())){
                receiverChatBox.put(senderUser.getUserName(),new LinkedList<>());
                LinkedList<Chat> curUserFriendChat=receiverChatBox.get(senderUser.getUserName());
                curUserFriendChat.add(curChat);
            }
            else {
                LinkedList<Chat> curUserFriendChat = receiverChatBox.get(senderUser.getUserName());
                curUserFriendChat.add(curChat);
            }

            //return ResponseEntity.created(new URI(curChat.getSender())).body(curChat);

        }

        return new ResponseEntity<String>("msg Sent", HttpStatus.OK);
        //return (ResponseEntity<Chat>) ResponseEntity.status(HttpStatus.OK);

    }

    @GetMapping("/getDialogBox/{userId}")
    public ResponseEntity<Map<String, HashMap<String, LinkedList<Chat>>>> getDialogBox(@PathVariable String userId) throws URISyntaxException {
        if(userDetails.containsKey((userId))){

            User curUser=userDetails.get(userId);

            return ResponseEntity.created(new URI(curUser.getContact())).body(curUser.getMessageQueue());
        }

        return null;
    }

//    @PutMapping("/addMember/{groupName}")
//    public ResponseEntity<Void> addMember(@RequestBody String userId,@PathVariable String groupName){
//        if(groupDetails.containsKey(groupName)){
//
//            Group curGp=groupDetails.get(groupName);
//            User curUserDetail=userDetails.get(userId);
//
//
//        }
//    }

    @GetMapping("/getUserDetails/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable String userId) throws URISyntaxException {
        if(userDetails.containsKey(userId)){
            User curUser=userDetails.get(userId);
            return ResponseEntity.created(new URI(curUser.getContact())).body(curUser);
        }

        return null;
    }



}
