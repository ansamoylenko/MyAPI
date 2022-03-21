package com.example.MyAPI.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Schema(description = "Структура тела запроса на удаления по id или логину")
public class DeleteBody
{
    @Schema(description = "Уникальный идентификатор", example = "12")
    private int id;
    @Schema(description = "Логин", example = "alex")
    private String login;
    @Schema(description = "Ппроль", example = "1234")
    private  String password;
}
