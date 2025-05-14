package com.socketverse.backend.messagesapi.users.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.openapitools.model.User;

@Data
@Entity
public class UserEntity {
    @Id
    @GeneratedValue
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "username")
    private String username;

    @JsonProperty(value = "firstname")
    private String firstname;

    @JsonProperty(value = "lastname")
    private String lastname;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "avatar_url")
    private String avatarUrl;

    public UserEntity(){}

    public UserEntity(User user){
        this.username = user.getUsername();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.avatarUrl = null;
    }
}
