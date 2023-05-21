package org.application.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;
import org.application.dtos.AccountCreationForm;
import org.application.exceptions.EncryptionException;


@Table(name="accounts")
@ToString
@Getter
@Entity
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private byte[] password;
    private byte[] salt;
    private String surname;
    private String name;
    private String patronymic;

    public AccountEntity(){

    }

    public AccountEntity(AccountCreationForm form){
        this.email = form.getEmail();
        this.surname = form.getSurname();
        this.name = form.getName();
        this.patronymic = form.getPatronymic();
    }

    @SneakyThrows
    public void setPassword(byte[] newPassword){
        if (newPassword == null || newPassword.length == 0) throw new EncryptionException("encryption failed");
        password = newPassword;
    }

    @SneakyThrows
    public void setSalt(byte[] newSalt){
        if (newSalt == null || newSalt.length == 0) throw new EncryptionException("encryption failed");
        salt = newSalt;
    }
}
