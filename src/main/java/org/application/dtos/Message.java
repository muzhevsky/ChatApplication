package org.application.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class Message {
    @Id
    private Integer id;
    private int senderId;
    private String text;
}
