package com.socketverse.backend.messagesapi.chats;

import com.socketverse.backend.messagesapi.chats.data.ChatEntity;
import com.socketverse.backend.messagesapi.chats.services.ChatService;
import jakarta.websocket.server.PathParam;
import org.openapitools.model.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/SocketVerse/chats")
public class ChatController {
    @Autowired
    private ChatService service;

    @PostMapping
    public ResponseEntity<?> createChat(@RequestBody Chat chat) {

        service.createNewChat(chat);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllChats(@PathParam(value = "userId") String userId) {

        List<ChatEntity> result = service.getAllChats(userId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateChat(@PathParam(value = "chatId") String chatId, @RequestBody Chat chat) {

        service.updateChat(chatId, chat);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
