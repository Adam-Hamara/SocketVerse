package com.socketverse.backend.messagesapi.users.models;

import com.socketverse.backend.messagesapi.chats.data.ChatEntity;
import com.socketverse.backend.messagesapi.users.data.UserEntity;
import org.openapitools.model.Chat;
import org.openapitools.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
    @Query(value = "SELECT u FROM UserEntity u WHERE u.uniqueId = :uniqueId")
    Optional<UserEntity> findByUniqueId(@Param("uniqueId") String uniqueId);

    @Query(value = "SELECT u FROM UserEntity u WHERE u.email = :email")
    Optional<UserEntity> findByEmail(@Param("email") String email);
}
