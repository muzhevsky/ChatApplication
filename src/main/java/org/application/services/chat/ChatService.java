package org.application.services.chat;

import org.application.entities.ChatEntity;
import org.application.repos.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    @Autowired
    ChatRepository repo;
    public boolean init(int userId){
        if (repo.findFirstByUserId(userId) == null)
            repo.save(new ChatEntity(userId));
        return true;
    }
}
