package com.socketverse.backend.messagesapi.messages.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.socketverse.backend.helper.SecureRandomGenerator;
import com.socketverse.backend.messagesapi.chats.data.ChatEntity;
import com.socketverse.backend.messagesapi.users.data.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.openapitools.model.Message;

import java.time.LocalDateTime;

@Data
@Entity
public class MessageEntity {

    @Id
    @GeneratedValue
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "unique_id")
    private String uniqueId;

    @OneToOne
    @JsonProperty(value = "chat")
    private ChatEntity chat;

    @OneToOne
    @JsonProperty(value = "sender")
    private UserEntity sender;

    @Enumerated(EnumType.STRING)
    @JsonProperty(value = "type")
    private Message.TypeEnum type;

    @JsonProperty(value = "content")
    private String content;

    @JsonProperty(value = "media_url")
    private String mediaUrl;

    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @JsonProperty(value = "status")
    private Message.StatusEnum status;

    public MessageEntity(){}

    public MessageEntity(Message message, ChatEntity chat, UserEntity sender){
        this.uniqueId = SecureRandomGenerator.generateSecureUUID().toString();
        this.chat = chat;
        this.sender = sender;
        this.type = message.getType();
        this.status = message.getStatus();
        this.content = message.getContent();
        this.createdAt = LocalDateTime.now();
    }
}
