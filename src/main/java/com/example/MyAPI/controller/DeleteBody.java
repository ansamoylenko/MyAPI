package com.example.MyAPI.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeleteBody
{
    private int id;
    private String login;
    private  String password;
}
