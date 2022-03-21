package com.example.MyAPI.domain;

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
@AllArgsConstructor
@NoArgsConstructor


@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private  String login;
    private String password;
    private String name;
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
