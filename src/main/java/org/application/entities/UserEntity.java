package org.application.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Table(name="users")
@ToString
@Getter
@AllArgsConstructor
@Entity
public class UserEntity {
    @Id
    private Integer accountId;

    public UserEntity(){

    }
}
