package com.example.MyAPI.controller;

import com.example.MyAPI.domain.UserRepository;
import com.example.MyAPI.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BasicController
{
    @Autowired
    private final UserRepository userRepository;

    @GetMapping("/")
    public String hello()
    {
        return "Hello";
    }



    @PostMapping("user/add")
    public ResponseEntity<Integer> add(@RequestBody User user)
    {
        User newUser = userRepository.save(user);
        return ResponseEntity.ok(newUser.getId());
    }


}
