package org.application.dtos;


import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class AccountCreationForm {
     private String email;
     private String password;
     private String surname;
     private String name;
     private String patronymic;
}
