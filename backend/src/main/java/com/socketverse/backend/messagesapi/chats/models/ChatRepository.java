package com.socketverse.backend.messagesapi.chats.models;

import com.socketverse.backend.messagesapi.chats.data.ChatEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends CrudRepository<ChatEntity, Long> {
    @Query(value = "SELECT c FROM ChatEntity c WHERE c.uniqueId = :uniqueId")
    Optional<ChatEntity> findByUniqueId(@Param("uniqueId") String uniqueId);

    @Query(value = "SELECT c FROM ChatEntity c JOIN c.participants p WHERE p.uniqueId = :uniqueId")
    Optional<List<ChatEntity>> getAllChatsForUser(@Param("uniqueId") String uniqueId);

    @Query("SELECT c FROM ChatEntity c JOIN c.participants p1 JOIN c.participants p2 WHERE p1.uniqueId = :user1UniqueId AND p2.uniqueId = :user2UniqueId")
    List<ChatEntity> findChatsByParticipants(@Param("user1UniqueId") String user1UniqueId, @Param("user2UniqueId") String user2UniqueId);
}
