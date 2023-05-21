package org.application.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;

@ToString
@Entity(name = "chats")
@Getter
public class ChatEntity {
    @Id
    private Integer userId;
    private Integer activeEmployeeId;

    public ChatEntity(){

    }

    public ChatEntity(int userId){
        this.userId = userId;
    }
}
