package com.example.MyAPI.controller;

import com.example.MyAPI.domain.User;
import com.example.MyAPI.domain.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController
{
    @Autowired
    private final UserRepository userRepository;


    @PostMapping("add")
    @Operation(
            summary = "Добавление пользователя",
            description = "API принимает в себя данные (логин, пароль, имя, фамилия) и записывает это в базу данных"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added new user",
                    content = { @Content(schema = @Schema(example = "Added new user with id: 12"))}),
            @ApiResponse(responseCode = "403", description = "Such user already exists",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid user data",
                    content = @Content) })
    public ResponseEntity<String> add(@RequestBody User user)
    {
        if(user.getLogin() == null || user.getPassword() == null)
            return new ResponseEntity<>("Invalid user data", HttpStatus.BAD_REQUEST);

        if(userRepository.findDistinctByLogin(user.getLogin()) != null)
            return new ResponseEntity<>("Such user already exists", HttpStatus.FORBIDDEN);
        else
        {
            userRepository.save(user);
            return ResponseEntity.ok("Added new user with id: " + user.getId());
        }
    }

    @PostMapping("delete")
    @Operation(
            summary = "Удаление пользователя",
            description = "API принимает в себя логин пользователя или его id и пароль для доступа, после чего удаляет из базы данных данные"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted",
                    content = { @Content(schema = @Schema(example = "User Иван Иванов is deleted"))}),
            @ApiResponse(responseCode = "404", description = "User is not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid password",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content) })
    public ResponseEntity<String> delete(@RequestBody DeleteBody deleteBody)
    {
        User user;
        if(deleteBody.getId() != 0)
            user = userRepository.findDistinctById(deleteBody.getId());
        else if(deleteBody.getLogin() != null)
            user = userRepository.findDistinctByLogin(deleteBody.getLogin());
        else
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);


        if(user == null)
            return new ResponseEntity<>("User is not found", HttpStatus.NOT_FOUND);
        if(user.getPassword().equals(deleteBody.getPassword()))
        {
            String response = "User " + user.getName() + " " + user.getLastname() + " is deleted";
            userRepository.delete(user);
            return ResponseEntity.ok(response);
        }
        else return new ResponseEntity<>("Invalid password", HttpStatus.FORBIDDEN);
    }

    @PostMapping("find")
    @Operation(
            summary = "Проверка наличия пользователя ",
            description = "API принимает в себя логин пользователя и отдает ответ, содержащий информацию есть пользователь, или нет в базе"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found: Иван Иванов",
                    content = { @Content(schema = @Schema(example = "Added new user with id: 12"))}),
            @ApiResponse(responseCode = "404", description = "User is not found",
                    content = @Content)})

    public ResponseEntity<String> find(@RequestParam(name = "login") String login)
    {
        User user = userRepository.findDistinctByLogin(login);

        if(user == null)
            return new ResponseEntity<>("User is not found", HttpStatus.NOT_FOUND);
        else
            return ResponseEntity.ok("User found: " + user.getName() + " " + user.getLastname());
    }

    @GetMapping("showAll")
    @Operation(
            summary = "Показать всех пользователей ",
            description = "API отправляет ответ, содержащий информацию обо всех пользователях"
    )
    @ApiResponse(responseCode = "200", description = "OK",
            content = { @Content(schema = @Schema(example = "id: 1, login alex, Александр Самойленко"))})

    public ResponseEntity<String> showAll()
    {
        Iterable<User> users = userRepository.findAll();

        StringBuilder response = new StringBuilder();

        users.forEach(user -> {
            response.append("id: " + user.getId());
            response.append(", login: " + user.getLogin());
            response.append(", " + user.getName() + " " + user.getLastname() + "\n");
        });

        return ResponseEntity.ok(response.toString());
    }

    @PostMapping ("findAll")
    @Operation(
            summary = "Поиск по пользователям",
            description = "API принимает в себя часть имени пользователя и выводит всех пользователей, которые могут подходить"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = { @Content(schema = @Schema(example = "id: 1, login alex, Александр Самойленко"))}),
            @ApiResponse(responseCode = "404", description = "User is not found",
                    content = @Content)})

    public ResponseEntity<String> findAll(@RequestParam(name = "name") String name)
    {
        Iterable<User> users = userRepository.findAllByNameContainingIgnoreCase(name);

        StringBuilder response = new StringBuilder();

        users.forEach(user -> {
            response.append("id: " + user.getId());
            response.append(", login: " + user.getLogin());
            response.append(", " + user.getName() + " " + user.getLastname() + "\n");
        });

        if(response.length() == 0)
            return new ResponseEntity<>("Users not found", HttpStatus.NOT_FOUND);
        else
            return ResponseEntity.ok(response.toString());
    }







}
