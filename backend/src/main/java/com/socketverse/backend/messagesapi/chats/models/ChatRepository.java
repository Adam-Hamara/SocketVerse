package com.socketverse.backend.messagesapi.chats.models;

import com.socketverse.backend.messagesapi.chats.data.ChatEntity;
import org.openapitools.model.Chat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends CrudRepository<ChatEntity, Long> {
    @Query(value = "SELECT c FROM ChatEntity c WHERE c.id = :chatId")
    Optional<ChatEntity> findByChatId(@Param("chatId") String chatId);
    @Query(value = "SELECT c FROM ChatEntity c JOIN c.participants p WHERE p.id = :userId")
    Optional<List<ChatEntity>> getAllChatsForUser(@Param("userId") String userId);

}
