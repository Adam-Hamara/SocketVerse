package com.socketverse.backend.messagesapi.chats.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.socketverse.backend.messagesapi.users.data.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.openapitools.model.Chat;
import org.openapitools.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ChatEntity {
    @Id
    @GeneratedValue
    @JsonProperty(value = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @JsonProperty(value = "type")
    private Chat.TypeEnum type;

    @JsonProperty(value = "name")
    private String name;

    @OneToMany
    @JsonProperty(value = "participants")
    private List<UserEntity> participants;

    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;

    @JsonProperty(value = "updated_at")
    private LocalDateTime updatedAt;

    public ChatEntity(){}

    public ChatEntity(Chat chat, List<UserEntity> users){
        this.type = chat.getType();
        this.name = chat.getName();
        this.participants = users;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
    }
}
