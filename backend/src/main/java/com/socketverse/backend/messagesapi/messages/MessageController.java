package com.socketverse.backend.messagesapi.messages;

import com.socketverse.backend.messagesapi.messages.services.MessageService;
import com.socketverse.backend.messagesapi.users.services.UserService;
import jakarta.websocket.server.PathParam;
import org.openapitools.model.Message;
import org.openapitools.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/SocketVerse/messages")
public class MessageController {
    @Autowired
    private MessageService service;

    @PostMapping("/{chatId}/{userId}")
    public ResponseEntity<?> createMessage(@PathParam(value = "chatId") String chatId, @PathParam(value = "userId") String userId,@RequestBody Message message) {

        service.createMessage(chatId, userId, message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<?> getMessages(@PathParam(value = "chatId") String chatId) {

        List<Message> result = service.getMessages(chatId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{chatId}")
    public ResponseEntity<?> updateMessage(@PathParam(value = "chatId") String chatId, @RequestBody Message message) {

        service.updateMessage(chatId, message);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
