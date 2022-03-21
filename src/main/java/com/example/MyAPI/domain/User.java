package com.example.MyAPI.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Setter
@Getter
@NoArgsConstructor

@Schema(description = "Структура пользователя")
@Entity
public class User
{
    @Schema(description = "Уникальный идентификатор пользователя", example = "23", accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Schema(description = "Логин", example = "alex")
    private  String login;
    @Schema(description = "Пароль", example = "1234")
    private String password;
    @Schema(description = "Имя пользователя", example = "Александр")
    private String name;
    @Schema(description = "Фамилия пользователя", example = "Самойленко")
    private String lastname;

    public User(String login, String password, String name, String lastname) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
    }

//    public User(String login, String password)
//    {
//        this.login = login;
//        this.password = password;
//    }

}
