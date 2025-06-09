package com.socketverse.backend.messagesapi.users;

import com.socketverse.backend.messagesapi.users.data.UserEntity;
import com.socketverse.backend.messagesapi.users.models.UserRepository;
import com.socketverse.backend.messagesapi.users.services.UserService;
import org.openapitools.model.User;
import org.openapitools.model.WebsocketSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/SocketVerse/users")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<WebsocketSession> createUser(@RequestBody User user) {

        WebsocketSession session = service.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(session);
    }

    @GetMapping
    public ResponseEntity<?> getUser(@RequestParam(value = "email") String email) {

        UserEntity result = service.getUser(email);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        List<UserEntity> result = service.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {

        service.updateUser(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
