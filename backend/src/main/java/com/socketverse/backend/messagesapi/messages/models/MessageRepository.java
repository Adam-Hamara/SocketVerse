package com.socketverse.backend.messagesapi.messages.models;

import com.socketverse.backend.messagesapi.messages.data.MessageEntity;
import org.openapitools.model.Chat;
import org.openapitools.model.Message;
import org.openapitools.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, String> {

    @Query(value = "SELECT m FROM MessageEntity m JOIN m.chat c WHERE c.id = :chatId")
    List<Message> getAllByChatId(@Param("chatId") String chatId);
}
