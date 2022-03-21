package com.example.MyAPI.controller;

import com.example.MyAPI.domain.User;
import com.example.MyAPI.domain.UserRepository;
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
    public ResponseEntity<String> add(@RequestBody User user)
    {
        if(user == null)
            return new ResponseEntity<>("Invalid user data", HttpStatus.FORBIDDEN);

        if(userRepository.findDistinctByLogin(user.getLogin()) != null)
            return new ResponseEntity<>("Such user already exists", HttpStatus.FORBIDDEN);
        else
        {
            userRepository.save(user);
            return ResponseEntity.ok("Added new user with id: " + user.getId());
        }
    }

    @PostMapping("delete")
    public ResponseEntity<String> delete(@RequestBody DeleteBody deleteBody)
    {
        User user;
        if(deleteBody.getId() != 0)
            user = userRepository.findDistinctById(deleteBody.getId());
        else if(deleteBody.getLogin() != null)
            user = userRepository.findDistinctByLogin(deleteBody.getLogin());
        else
            return new ResponseEntity<>("Invalid request", HttpStatus.FORBIDDEN);


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
    public ResponseEntity<String> find(@RequestParam(name = "login") String login)
    {
        User user = userRepository.findDistinctByLogin(login);

        if(user == null)
            return new ResponseEntity<>("User is not found", HttpStatus.NOT_FOUND);
        else
            return ResponseEntity.ok("User found: " + user.getName() + " " + user.getLastname());
    }

    @GetMapping("show")
    public ResponseEntity<String> show()
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
