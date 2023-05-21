package org.application.repos;

import org.application.entities.ChatEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends CrudRepository<ChatEntity, Integer>{
    ChatEntity findFirstByUserId(Integer userId);
}
