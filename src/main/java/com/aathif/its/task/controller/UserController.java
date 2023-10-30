package com.aathif.its.task.controller;

import com.aathif.its.task.dto.LoginDTO;
import com.aathif.its.task.dto.UpdateUserDTO;
import com.aathif.its.task.dto.UserDTO;
import com.aathif.its.task.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/load")
    public ResponseEntity<?> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO){
        return userService.register(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        return userService.login(loginDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateUserDTO updateUserDTO){
        return userService.update(updateUserDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(){
        return userService.delete();
    }

}
