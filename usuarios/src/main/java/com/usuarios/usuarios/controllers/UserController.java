package com.usuarios.usuarios.controllers;

import com.usuarios.usuarios.dtos.UserDto;
import com.usuarios.usuarios.dtos.newDto.NewUserDto;
import com.usuarios.usuarios.dtos.updates.UpdateUser;
import com.usuarios.usuarios.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<UserDto>getById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>>getAll(){
        return ResponseEntity.ok(userService.getAll());
    }
    @PostMapping("/register")
    public ResponseEntity<UserDto>registerNewUser(@RequestBody NewUserDto newUserDto){
        return ResponseEntity.ok(userService.registerUser(newUserDto));
    }
    @PutMapping("/updateUser")
    public ResponseEntity<UserDto>updateUser(@RequestBody UpdateUser user){
        return ResponseEntity.ok(userService.updateUser(user));
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<UserDto>deleteUser(@PathVariable Long id){
        return  ResponseEntity.ok(userService.deleteUser(id));
    }
}
