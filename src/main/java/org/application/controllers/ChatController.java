package org.application.controllers;

import org.application.dtos.Message;
import org.application.entities.AccountEntity;
import org.application.repos.AccountRepository;
import org.application.services.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat")
    @SendToUser("/topic/messages")
    @CrossOrigin(origins = "http://localhost:3000")
    public Message receiveMessage(@RequestBody Message message){
        return message;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/join/{userId}")
    public ResponseEntity<AccountEntity> join(@PathVariable("userId") Integer userId){
        return new ResponseEntity<>(accountRepository.findById(userId).get(), HttpStatus.OK);
    }
}
